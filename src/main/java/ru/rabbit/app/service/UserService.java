package ru.rabbit.app.service;

import ru.rabbit.app.entity.Topic;
import ru.rabbit.app.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    Optional<User> getCurrentUser();

    Set<Topic> getUserTopics(Optional<User> user);

    Set<Topic> getUserTopics();
}
