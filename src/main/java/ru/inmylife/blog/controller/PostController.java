package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.inmylife.blog.dto.block.PostData;
import ru.inmylife.blog.service.PostService;
import ru.inmylife.blog.service.SessionService;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final UserService userService;

    private final SessionService sessionService;

    @GetMapping
    public String posts(Model model) {
        val user = userService.getCurrentUser();
        model.addAttribute("isAuth", sessionService.isAuthenticated());
        model.addAttribute("posts", postService.getPosts(user));
        model.addAttribute("topics", userService.getUserTopics(user));

        return "public/index";
    }

    @GetMapping("/post/{linkText}")
    public String post(@PathVariable("linkText") String linkText, Model model) {
        model.addAttribute("isAuth", sessionService.isAuthenticated());
        model.addAttribute("post", postService.findPost(linkText));
        model.addAttribute("topics", userService.getUserTopics());

        return "public/post";
    }

    @PostMapping("/post/create")
    public ResponseEntity<String> create(@RequestBody PostData postData) {
        userService.getCurrentUser().ifPresent(value -> postService.create(postData, value));

        return ResponseEntity.ok("OK");
    }
}
