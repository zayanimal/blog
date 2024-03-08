package ru.inmylife.blog.service;


import ru.inmylife.blog.dto.block.Post;

import java.util.List;

public interface PostService {

    Post findPost(Long id);

    List<Post> getPosts();

    void create(Post post);

    void update(Long id, Post post);
}
