package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.inmylife.blog.service.PostService;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "public/index";
    }
}
