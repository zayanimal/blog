package ru.inmylife.blog.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface SessionService {

    Boolean isAuthenticated();

    Optional<String> getUserName();

    void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication);
}
