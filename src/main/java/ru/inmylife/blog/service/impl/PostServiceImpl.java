package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.inmylife.blog.dto.block.Block;
import ru.inmylife.blog.dto.block.BlockData;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.Post;
import ru.inmylife.blog.entity.User;
import ru.inmylife.blog.exception.TitleNotFoundException;
import ru.inmylife.blog.exception.TopicNotFoundException;
import ru.inmylife.blog.repository.PostJpaRepository;
import ru.inmylife.blog.service.LinkService;
import ru.inmylife.blog.service.PostService;
import ru.inmylife.blog.service.UserService;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostJpaRepository postJpaRepository;

    private final UserService userService;

    private final LinkService linkService;

    @Override
    public Mono<PostData> findPost(String linkText) {
        return Mono
            .fromCallable(() -> postJpaRepository
                .findPostByLinkText(linkText)
                .map(this::mapPost))
            .subscribeOn(Schedulers.boundedElastic())
            .flatMap(Mono::justOrEmpty);
    }

    @Override
    public Flux<PostData> getPosts(Mono<User> user) {
        return Mono
            .fromCallable(() -> {
                val topicsSet = userService.getUserTopics(user).collect(Collectors.toSet())
                    .defaultIfEmpty(new HashSet<>())
                    .block();

                return Objects.isNull(topicsSet) || topicsSet.isEmpty()
                    ? postJpaRepository.findAllByOrderByCreatedDesc()
                    : postJpaRepository.findAllByTopicInOrderByCreatedDesc(topicsSet);

            })
            .subscribeOn(Schedulers.boundedElastic())
            .flatMapMany(Flux::fromIterable)
            .map(this::mapPost)
            .map(post -> post.setBlocks(post.getBlocks().stream().limit(2).toList()))
            .filterWhen(p -> isVisiblePost(p, user));
    }

    private PostData mapPost(Post post) {
        return new PostData()
            .setId(post.getId())
            .setUsername(post.getUser().getUsername())
            .setTopic(post.getTopic().getName())
            .setBlocks(post.getBlocks())
            .setLinkText(post.getLinkText())
            .setIsPublic(post.getIsPublic())
            .setDate(getFormattedDate(post.getCreated().toLocalDate()));
    }

    private String getFormattedDate(LocalDate date) {
        val locale = new Locale("ru", "RU");
        val dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        return dateFormat.format(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    private Mono<Boolean> isVisiblePost(PostData post, Mono<User> user) {
        return user
            .map(User::getUsername)
            .filter(username -> post.getUsername().equals(username))
            .hasElement()
            .map(isUserPost -> isUserPost || post.getIsPublic());
    }

    @Override
    public Mono<Boolean> create(PostData postData, User user) {
        val blocks = postData.getBlocks();
        val linkText = getLinkText(blocks);
        return Mono.fromCallable(() -> postJpaRepository.save(new Post()
            .setCreated(ZonedDateTime.now())
            .setUser(user)
            .setIsPublic(postData.getIsPublic())
            .setTopic(user.getTopics().stream()
                .filter(t -> Objects.equals(t.getName(), postData.getTopic()))
                .findFirst()
                .orElseThrow(() -> new TopicNotFoundException("Тема не соответствует темам пользователя")))
            .setBlocks(blocks)
            .setLinkText(linkText)))
            .subscribeOn(Schedulers.boundedElastic())
            .thenReturn(true);
    }

    private String getLinkText(List<Block> blocks) {
        return blocks.stream()
            .findFirst()
            .map(Block::getData)
            .map(BlockData::getText)
            .map(linkService::transliterate)
            .orElseThrow(() -> new TitleNotFoundException("Заголовок поста не найден"));
    }
}
