package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.service.PostService;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping
    public Rendering posts() {
        val topics = userService.getUserTopics();
        return Rendering.view("public/index")
            .modelAttribute("posts", postService.getPosts(topics))
            .modelAttribute("topics", topics)
            .build();
    }

    @GetMapping("/post/{id}")
    public Rendering post(@PathVariable("id") Long id) {
        return Rendering.view("public/post")
            .modelAttribute("post", postService.findPost(id))
            .modelAttribute("topics", userService.getUserTopics())
            .build();
    }

    @PostMapping("/post/create")
    public Mono<ResponseEntity<String>> create(@RequestBody PostData postData) {
        return userService.getCurrentUser()
            .flatMap(user -> postService.create(postData, user))
            .thenReturn(ResponseEntity.ok("OK"));
    }

    @PostMapping("/post/{id}")
    public Mono<ResponseEntity<String>> update(@PathVariable("id") Long id, @RequestBody PostData postData) {
        return postService.update(id, postData)
            .thenReturn(ResponseEntity.ok("OK"));
    }
}
