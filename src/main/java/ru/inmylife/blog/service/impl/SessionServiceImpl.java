package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.service.SessionService;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SecurityContextServerLogoutHandler logoutHandler;

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

    @Override
    public Mono<Void> logout(ServerWebExchange exchange, Authentication authentication) {
        return logoutHandler.logout(new WebFilterExchange(exchange, chain -> Mono.empty()), authentication);
    }

}
