package ru.inmylife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inmylife.blog.entity.Post;
import ru.inmylife.blog.entity.Topic;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();

    List<Post> findAllByTopicInOrderByCreatedDesc(Set<Topic> topics);

    Optional<Post> findPostByLinkText(String linkText);
}
