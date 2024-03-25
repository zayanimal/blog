package ru.inmylife.blog.service.impl;

import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.service.SessionService;

import java.util.Optional;

@Service
public class SessionServiceImpl implements SessionService {

    @Override
    public Optional<String> getUserName() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken
            ? Optional.empty()
            : Optional.of(authentication.getName());
    }
}
