package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.inmylife.blog.service.PostService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String post(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("post", postService.getPostById(id));
        return "public/post";
    }
}
