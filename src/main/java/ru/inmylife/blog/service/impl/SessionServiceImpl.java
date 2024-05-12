package ru.inmylife.blog.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.service.SessionService;

import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    private static final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Override
    public Boolean isAuthenticated() {
        return getAuthentication()
            .map(Authentication::isAuthenticated)
            .orElse(Boolean.FALSE);
    }

    @Override
    public Optional<String> getUserName() {
        return getAuthentication().map(Authentication::getName);
    }

    private Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutHandler.logout(request, response, authentication);
    }

}
