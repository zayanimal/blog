package ru.inmylife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inmylife.blog.entity.Topic;

import java.util.Optional;
import java.util.UUID;

public interface TopicJpaRepository extends JpaRepository<Topic, UUID> {

    Optional<Topic> findTopicByNameContaining(String name);
}
