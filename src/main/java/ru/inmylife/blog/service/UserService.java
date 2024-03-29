package ru.inmylife.blog.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.entity.User;

public interface UserService {

    Mono<User> getCurrentUser();

    Flux<Topic> getUserTopics();
}
