package ru.rabbit.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rabbit.app.entity.Post;
import ru.rabbit.app.entity.Topic;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();

    List<Post> findAllByTopicInOrderByCreatedDesc(Set<Topic> topics);

    Optional<Post> findPostByLinkText(String linkText);
}
