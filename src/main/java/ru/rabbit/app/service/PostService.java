package ru.rabbit.app.service;

import ru.rabbit.app.dto.block.PostData;
import ru.rabbit.app.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService {

    PostData findPost(String linkText);

    List<PostData> getPosts(Optional<User> user);

    void create(PostData postData, User user);
}
