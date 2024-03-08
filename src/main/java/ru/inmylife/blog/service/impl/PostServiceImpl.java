package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
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
            .map(this::mapPost)
            .orElse(null);
    }

    @Override
    public List<Post> getPosts() {
        return postJpaRepository.findAll().stream()
            .map((e) -> {
                val post = mapPost(e);
                post.setBlocks(post.getBlocks().stream().limit(2).toList());
                return post;
            })
            .collect(Collectors.toList());
    }

    private Post mapPost(PostEntity entity) {
        val post = entity.getPost();
        post.setId(entity.getId());
        post.setIsPublic(entity.getIsPublic());
        return post;
    }

    @Override
    public void create(Post post) {
        postJpaRepository.save(new PostEntity()
            .setCreated(ZonedDateTime.now())
            .setIsPublic(true)
            .setPost(post));
    }

    @Override
    public void update(Long id, Post post) {
        val isPublic = post.getIsPublic();
        post.setIsPublic(null);
        postJpaRepository.update(id, post, isPublic);
    }
}
