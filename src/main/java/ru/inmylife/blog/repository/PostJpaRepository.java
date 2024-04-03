package ru.inmylife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.Post;
import ru.inmylife.blog.entity.Topic;

import java.util.List;
import java.util.Set;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByOrderByCreatedDesc();

    List<Post> findAllByTopicInOrderByCreatedDesc(Set<Topic> topics);

    @Modifying
    @Query("update Post p set p.postData = :data, p.isPublic = :isPublic where p.id = :id")
    void update(@Param("id") Long id, @Param("data") PostData data, @Param("isPublic") Boolean isPublic);
}
