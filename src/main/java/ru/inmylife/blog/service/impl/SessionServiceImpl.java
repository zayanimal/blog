package ru.inmylife.blog.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.service.SessionService;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public Mono<Boolean> isAuthenticated() {
        return getAuthentication().map(Authentication::isAuthenticated)
            .switchIfEmpty(Mono.just(Boolean.FALSE));
    }

    @Override
    public Mono<String> getUserName() {
        return getAuthentication().map(Authentication::getName);
    }

    private Mono<Authentication> getAuthentication() {
        return ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication);
    }
}
