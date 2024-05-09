package ru.inmylife.blog.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class RememberMeFilter implements WebFilter {

    private final ReactiveAuthenticationManager authenticationManager;

    private final ServerSecurityContextRepository securityContextRepository;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
            .map(SecurityContext::getAuthentication)
            .switchIfEmpty(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("petryuk", "123")))
            .flatMap((authentication) -> onAuthenticationSuccess(authentication, new WebFilterExchange(exchange, chain)));
    }

    protected Mono<Void> onAuthenticationSuccess(Authentication authentication, WebFilterExchange webFilterExchange) {
        val securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(authentication);

        return securityContextRepository.save(webFilterExchange.getExchange(), securityContext)
            .then(webFilterExchange.getChain().filter(webFilterExchange.getExchange())
                .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(securityContext))));
    }
}
