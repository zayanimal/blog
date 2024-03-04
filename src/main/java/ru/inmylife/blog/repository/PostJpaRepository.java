package ru.inmylife.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inmylife.blog.entity.PostEntity;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> { }
