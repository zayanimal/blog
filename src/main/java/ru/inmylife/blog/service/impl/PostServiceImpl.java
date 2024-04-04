package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.Post;
import ru.inmylife.blog.entity.User;
import ru.inmylife.blog.repository.PostJpaRepository;
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

    @Override
    public Mono<PostData> findPost(Long id) {
        return Mono
            .fromCallable(() -> postJpaRepository.findById(id).map(this::mapPost))
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
        val postData = post.getPostData();
        postData.setId(post.getId());
        postData.setUsername(post.getUser().getUsername());
        postData.setTopic(post.getTopic().getName());
        postData.setIsPublic(post.getIsPublic());
        postData.setDate(getFormattedDate(post.getCreated().toLocalDate()));
        return postData;
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
        return Mono.fromCallable(() -> postJpaRepository.save(new Post()
            .setCreated(ZonedDateTime.now())
            .setUser(user)
            .setIsPublic(postData.getIsPublic())
            .setTopic(user.getTopics().stream()
                .filter(t -> Objects.equals(t.getName(), postData.getTopic()))
                .findFirst()
                .orElse(null))
            .setPostData(postData)))
            .subscribeOn(Schedulers.boundedElastic())
            .thenReturn(true);
    }

    @Override
    public Mono<Boolean> update(Long id, PostData postData) {
        val isPublic = postData.getIsPublic();
        postData.setIsPublic(null);
        return Mono.fromCallable(() -> {
            postJpaRepository.update(id, postData, isPublic);
            return true;
        })
        .subscribeOn(Schedulers.boundedElastic());
    }
}
