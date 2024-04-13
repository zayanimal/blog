package ru.inmylife.blog.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public interface SessionService {

    Mono<Boolean> isAuthenticated();

    Mono<String> getUserName();

    Mono<Void> logout(ServerWebExchange exchange, Authentication authentication);
}
