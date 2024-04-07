package ru.inmylife.blog.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.User;

public interface PostService {

    Mono<PostData> findPost(String linkText);

    Flux<PostData> getPosts(Mono<User> user);

    Mono<Boolean> create(PostData postData, User user);
}
