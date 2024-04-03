package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public Rendering getAdmin() {
        return Rendering.view("admin/index")
            .modelAttribute("topics", userService.getUserTopics().map(Topic::getName))
            .build();
    }
}
