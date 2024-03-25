package ru.inmylife.blog.service;

import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<User> getCurrentUser();

    Set<Topic> getUserTopics();
}
