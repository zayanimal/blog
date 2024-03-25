package ru.inmylife.blog.service;

import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.entity.Topic;

import java.util.List;
import java.util.Set;

public interface PostService {

    PostData findPost(Long id);

    List<PostData> getPosts(Set<Topic> topics);

    void create(PostData postData);

    void update(Long id, PostData postData);
}
