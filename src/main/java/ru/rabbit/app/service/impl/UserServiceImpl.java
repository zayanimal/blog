package ru.rabbit.app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.rabbit.app.entity.Topic;
import ru.rabbit.app.entity.User;
import ru.rabbit.app.repository.UserJpaRepository;
import ru.rabbit.app.service.SessionService;
import ru.rabbit.app.service.UserService;

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
