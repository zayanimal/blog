package ru.inmylife.blog.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import ru.inmylife.blog.exception.UserNotFoundException;
import ru.inmylife.blog.repository.UserJpaRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserJpaRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .anonymous(AbstractHttpConfigurer::disable)
            .authenticationManager(authenticationManager())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/admin", "/post/create", "/upload").authenticated()
                .anyRequest().permitAll())
            .formLogin(form -> form.loginPage("/login"))
            .logout(logoutSpec -> logoutSpec
                .logoutUrl("/logout")
                .logoutSuccessHandler(new ForwardLogoutSuccessHandler("/")))
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        val authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService((username -> {
            val user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

            return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        }));

        return new ProviderManager(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
