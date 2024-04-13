package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.service.SessionService;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final SessionService sessionService;

    @GetMapping("/login")
    public Rendering loginForm() {
        return Rendering
            .view("public/login")
            .modelAttribute("isAuth", sessionService.isAuthenticated())
            .modelAttribute("topics", userService.getUserTopics())
            .build();
    }

    @GetMapping("/logout")
    public Mono<Rendering> logout(ServerWebExchange exchange, Authentication authentication) {
        return sessionService.logout(exchange, authentication)
            .thenReturn(Rendering.redirectTo("/").build());
    }
}
