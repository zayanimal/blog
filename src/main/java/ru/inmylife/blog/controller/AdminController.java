package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.inmylife.blog.entity.Topic;
import ru.inmylife.blog.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping
    public String getAdmin(Model model) {
        model.addAttribute("topics", userService.getUserTopics().stream().map(Topic::getName).toList());

        return "admin/index";
    }
}
