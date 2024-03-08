package ru.inmylife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.inmylife.blog.dto.block.Post;
import ru.inmylife.blog.entity.PostEntity;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    @Modifying
    @Query("update PostEntity p set p.post = :data, p.isPublic = :isPublic where p.id = :id")
    void update(@Param("id") Long id, @Param("data") Post data, @Param("isPublic") Boolean isPublic);
}
