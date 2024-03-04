package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.inmylife.blog.dto.block.Post;
import ru.inmylife.blog.service.PostService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    public String post(@PathVariable("id") Long id, Model model) {
        model.addAttribute("post", postService.findPost(id));
        return "public/post";
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> save(@PathVariable("id") Long id, @RequestBody Post post) {
        postService.saveOrUpdate(id, post);

        return ResponseEntity.ok("OK");
    }
}
