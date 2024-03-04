package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.inmylife.blog.dto.block.Post;
import ru.inmylife.blog.entity.PostEntity;
import ru.inmylife.blog.repository.PostJpaRepository;
import ru.inmylife.blog.service.PostService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post findPost(Long id) {
        return postJpaRepository.findById(id)
            .map(PostEntity::getPost)
            .orElse(null);
    }

    public List<Post> getPosts() {
        return postJpaRepository.findAll().stream()
            .map(PostEntity::getPost)
            .collect(Collectors.toList());
    }

    @Override
    public void saveOrUpdate(Long id, Post post) {
        postJpaRepository.save(new PostEntity()
            .setId(1L)
            .setCreated(ZonedDateTime.now())
            .setPost(post));
    }
}
