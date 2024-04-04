package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.entity.User;
import ru.inmylife.blog.repository.UserJpaRepository;
import ru.inmylife.blog.service.SessionService;
import ru.inmylife.blog.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SessionService sessionService;

    private final UserJpaRepository userRepository;

    @Override
    public Flux<Topic> getUserTopics() {
        return getUserTopics(getCurrentUser());
    }

    @Override
    public Flux<Topic> getUserTopics(Mono<User> user) {
        return user.flatMapMany(u -> Flux.fromIterable(u.getTopics()));
    }

    @Override
    public Mono<User> getCurrentUser() {
        return sessionService.getUserName()
            .flatMap(username -> Mono
                .fromCallable(() -> userRepository.findByUsername(username))
                .flatMap(Mono::justOrEmpty)
                .subscribeOn(Schedulers.boundedElastic()));
    }
}
