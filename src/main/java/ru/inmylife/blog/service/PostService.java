package ru.inmylife.blog.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.entity.User;

public interface PostService {

    Mono<PostData> findPost(Long id);

    Flux<PostData> getPosts(Flux<Topic> topics);

    void create(PostData postData, User user);

    void update(Long id, PostData postData);
}
