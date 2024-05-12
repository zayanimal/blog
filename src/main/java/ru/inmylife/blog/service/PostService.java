package ru.inmylife.blog.service;

import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostData findPost(String linkText);

    List<PostData> getPosts(Optional<User> user);

    void create(PostData postData, User user);
}
