package ru.inmylife.blog.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.exception.UserNotFoundException;
import ru.inmylife.blog.repository.UserJpaRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserJpaRepository userRepository;

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        http
            .authenticationManager(authenticationManager())
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/admin").authenticated()
                .pathMatchers("/post/create").authenticated()
                .pathMatchers("/**").permitAll())
            .formLogin(Customizer.withDefaults())
            .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        val authManager = new UserDetailsRepositoryReactiveAuthenticationManager(username -> Mono
            .justOrEmpty(userRepository.findByUsername(username))
            .map(usr -> User.builder()
                .username(usr.getUsername())
                .password(usr.getPassword())
                .build())
            .switchIfEmpty(Mono.error(() -> new UserNotFoundException("Пользователь не найден"))));

        authManager.setPasswordEncoder(passwordEncoder());

        return authManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
