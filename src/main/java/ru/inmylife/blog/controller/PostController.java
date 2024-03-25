package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.service.PostService;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    @GetMapping
    public String posts(Model model) {
        val topics = userService.getUserTopics();
        model.addAttribute("posts", postService.getPosts(topics));
        model.addAttribute("topics", topics);
        return "public/index";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.findPost(id));
        return "public/post";
    }

    @PostMapping("/post/create")
    public ResponseEntity<String> create(@RequestBody PostData postData) {
        postService.create(postData);

        return ResponseEntity.ok("OK");
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody PostData postData) {
        postService.update(id, postData);

        return ResponseEntity.ok("OK");
    }
}
