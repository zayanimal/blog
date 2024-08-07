package ru.rabbit.app.service.impl;

import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.rabbit.app.dto.block.Block;
import ru.rabbit.app.dto.block.BlockData;
import ru.rabbit.app.dto.block.PostData;
import ru.rabbit.app.entity.Post;
import ru.rabbit.app.entity.User;
import ru.rabbit.app.exception.BusinessException;
import ru.rabbit.app.exception.PostNotFoundException;
import ru.rabbit.app.exception.TitleNotFoundException;
import ru.rabbit.app.exception.TopicNotFoundException;
import ru.rabbit.app.repository.PostJpaRepository;
import ru.rabbit.app.service.LinkService;
import ru.rabbit.app.service.PostService;
import ru.rabbit.app.service.UserService;

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
    public PostData findPost(String linkText) {
        return postJpaRepository.findPostByLinkText(linkText)
            .map(this::mapPost)
            .orElseThrow(PostNotFoundException::new);
    }

    @Override
    public List<PostData> getPosts(Optional<User> user) {
        return Try.of(() -> userService.getUserTopics(user))
            .map(topics -> topics.isEmpty()
                ? postJpaRepository.findAllByOrderByCreatedDesc()
                : postJpaRepository.findAllByTopicInOrderByCreatedDesc(topics))
            .map(posts -> posts.stream()
                .map(this::mapPost)
                .map(post -> post.setBlocks(post.getBlocks().stream().limit(2).toList()))
                .filter(post -> isVisiblePost(post, user))
                .collect(Collectors.toList()))
            .getOrElseThrow(e -> new BusinessException("Не удалось загрузить посты", e));
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

    private boolean isVisiblePost(PostData post, Optional<User> user) {
        return user
            .map(User::getUsername)
            .map(username -> post.getUsername().equals(username))
            .map(isUserPost -> isUserPost || post.getIsPublic())
            .orElseGet(post::getIsPublic);
    }

    @Override
    public void create(PostData postData, User user) {
        val blocks = postData.getBlocks();
        val linkText = getLinkText(blocks);

        postJpaRepository.save(new Post()
            .setCreated(ZonedDateTime.now())
            .setUser(user)
            .setIsPublic(postData.getIsPublic())
            .setTopic(user.getTopics().stream()
                .filter(t -> Objects.equals(t.getName(), postData.getTopic()))
                .findFirst()
                .orElseThrow(() -> new TopicNotFoundException("Тема не соответствует темам пользователя")))
            .setBlocks(blocks)
            .setLinkText(linkText));
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
