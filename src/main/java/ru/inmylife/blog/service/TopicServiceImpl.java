package ru.inmylife.blog.service;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.entity.User;
import ru.inmylife.blog.repository.UserJpaRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final UserJpaRepository userRepository;

    public Set<Topic> getTopics() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return userRepository.findByUsername(authentication.getName())
                .map(User::getTopics)
                .orElseGet(Set::of);
        }

        return Set.of();
    }
}
