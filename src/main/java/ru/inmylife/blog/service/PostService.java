package ru.inmylife.blog.service;

import ru.inmylife.blog.dto.Post;

import java.util.List;

public interface PostService {

    Post getPostById(Integer id);

    List<Post> getPosts();
}
