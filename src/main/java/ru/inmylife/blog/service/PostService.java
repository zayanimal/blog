package ru.inmylife.blog.service;


import ru.inmylife.blog.dto.block.PostData;

import java.util.List;

public interface PostService {

    PostData findPost(Long id);

    List<PostData> getPosts();

    void create(PostData postData);

    void update(Long id, PostData postData);
}
