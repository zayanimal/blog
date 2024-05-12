package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.entity.User;
import ru.inmylife.blog.repository.UserJpaRepository;
import ru.inmylife.blog.service.SessionService;
import ru.inmylife.blog.service.UserService;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SessionService sessionService;

    private final UserJpaRepository userRepository;

    @Override
    public Set<Topic> getUserTopics() {
        return getCurrentUser()
            .map(User::getTopics)
            .orElseGet(Set::of);
    }

    @Override
    public Set<Topic> getUserTopics(Optional<User> user) {
        return user
            .map(User::getTopics)
            .orElse(Set.of());
    }

    @Override
    public Optional<User> getCurrentUser() {
        return sessionService.getUserName().flatMap(userRepository::findByUsername);
    }
}
