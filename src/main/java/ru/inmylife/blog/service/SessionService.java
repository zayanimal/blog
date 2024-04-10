package ru.inmylife.blog.service;

import reactor.core.publisher.Mono;

public interface SessionService {

    Mono<Boolean> isAuthenticated();

    Mono<String> getUserName();
}
