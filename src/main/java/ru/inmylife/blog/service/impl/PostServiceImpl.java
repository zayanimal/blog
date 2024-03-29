package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.Post;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.repository.PostJpaRepository;
import ru.inmylife.blog.service.PostService;

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

    @Override
    public Mono<PostData> findPost(Long id) {
        return Mono.justOrEmpty(postJpaRepository.findById(id).map(this::mapPost));
    }

    @Override
    public Flux<PostData> getPosts(Flux<Topic> topics) {
        val topicsSet = topics.collect(Collectors.toSet()).defaultIfEmpty(new HashSet<>()).block();

        return Objects.isNull(topicsSet) || topicsSet.isEmpty()
            ? getPostData(postJpaRepository.findAllByOrderByCreatedDesc())
            : getPostData(postJpaRepository.findAllByTopicsInOrderByCreatedDesc(topicsSet));
    }

    private Flux<PostData> getPostData(List<Post> posts) {
        return Flux.fromIterable(posts)
            .map((e) -> {
                val post = mapPost(e);
                post.setBlocks(post.getBlocks().stream().limit(2).toList());
                return post;
            });
    }

    private PostData mapPost(Post post) {
        val postData = post.getPostData();
        postData.setId(post.getId());
        postData.setUsername(post.getUser().getUsername());
        postData.setTopics(post.getTopics().stream().map(Topic::getName).collect(Collectors.toSet()));
        postData.setIsPublic(post.getIsPublic());
        postData.setDate(getFormattedDate(post.getCreated().toLocalDate()));
        return postData;
    }

    private String getFormattedDate(LocalDate date) {
        val locale = new Locale("ru", "RU");
        val dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, locale);
        return dateFormat.format(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    public void create(PostData postData) {
        postJpaRepository.save(new Post()
            .setCreated(ZonedDateTime.now())
            .setIsPublic(true)
            .setPostData(postData));
    }

    @Override
    public void update(Long id, PostData postData) {
        val isPublic = postData.getIsPublic();
        postData.setIsPublic(null);
        postJpaRepository.update(id, postData, isPublic);
    }
}
