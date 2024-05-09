package ru.inmylife.blog.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.SecurityContextServerLogoutHandler;
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.inmylife.blog.exception.UserNotFoundException;
import ru.inmylife.blog.repository.UserJpaRepository;

import java.net.URI;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserJpaRepository userRepository;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http
            .securityContextRepository(securityContextRepository())
            .authenticationManager(authenticationManager())
//            .addFilterBefore(new RememberMeFilter(authenticationManager(), securityContextRepository()), AUTHENTICATION)
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/admin").authenticated()
                .pathMatchers("/post/create").authenticated()
                .pathMatchers("/**").permitAll())
            .formLogin(formLoginSpec -> formLoginSpec.loginPage("/login"))
            .logout(logoutSpec -> logoutSpec.logoutUrl("/logout")
                .logoutSuccessHandler(buildLogoutHandler())
                .logoutHandler(logoutHandler()))
            .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    private RedirectServerLogoutSuccessHandler buildLogoutHandler() {
        val redirectServerLogoutSuccessHandler = new RedirectServerLogoutSuccessHandler();
        redirectServerLogoutSuccessHandler.setLogoutSuccessUrl(URI.create("/"));
        return redirectServerLogoutSuccessHandler;
    }

    @Bean
    public WebSessionServerSecurityContextRepository securityContextRepository() {
        return new WebSessionServerSecurityContextRepository();
    }

    @Bean
    public SecurityContextServerLogoutHandler logoutHandler() {
        return new SecurityContextServerLogoutHandler();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        val authManager = new UserDetailsRepositoryReactiveAuthenticationManager(username -> Mono
            .fromCallable(() -> userRepository.findByUsername(username))
            .flatMap(Mono::justOrEmpty)
            .subscribeOn(Schedulers.boundedElastic())
            .map(usr -> User.builder()
                .username(usr.getUsername())
                .password(usr.getPassword())
                .build())
            .switchIfEmpty(Mono.error(() -> new UserNotFoundException("Пользователь не найден"))));

        authManager.setPasswordEncoder(new BCryptPasswordEncoder(10));

        return authManager;
    }
}
