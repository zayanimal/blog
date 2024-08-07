package ru.rabbit.app.config;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import ru.rabbit.app.exception.UserNotFoundException;
import ru.rabbit.app.repository.UserJpaRepository;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserJpaRepository userRepository;

    private final PersistentTokenRepository persistentTokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .anonymous(AbstractHttpConfigurer::disable)
            .authenticationManager(authenticationManager())
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/post/create", "/upload").authenticated()
                .anyRequest().permitAll())
            .formLogin(form -> form.loginPage("/login"))
            .logout(logoutSpec -> logoutSpec
                .logoutUrl("/logout")
                .logoutSuccessHandler(new ForwardLogoutSuccessHandler("/")))
            .csrf(AbstractHttpConfigurer::disable)
            .rememberMe(rememberMe -> rememberMe.rememberMeServices(rememberMeServices()));
        return http.build();
    }

    @Bean
    public RememberMeAuthenticationFilter rememberMerememberMeFilterFilter() {
        return new RememberMeAuthenticationFilter(authenticationManager(), rememberMeServices());
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices() {
         return new PersistentTokenBasedRememberMeServices("springRocks", userDetailsService(), persistentTokenRepository);
    }

    @Bean
    RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
        return new RememberMeAuthenticationProvider("springRocks");
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        val authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService());

        return new ProviderManager(authProvider);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            val user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

            return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
