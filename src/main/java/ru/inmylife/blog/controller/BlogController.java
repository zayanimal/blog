package ru.inmylife.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("myPage", "This is my paaaaage");
        return "index";
    }
}
