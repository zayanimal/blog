package ru.inmylife.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.inmylife.blog.dto.Post;

import java.util.List;

@Controller
@RequestMapping("/")
public class BlogController {

    @GetMapping
    public String index(Model model) {
        model.addAttribute("posts", getPosts());
        return "index";
    }

    @GetMapping("/post")
    public String post(Model model) {
        model.addAttribute("post", Post.builder()
            .title("Прогулка по парку")
            .image("https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/bbbe4a30-2e49-452f-aaed-a3b4e05843a3/")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build());

        return "post";
    }

    private List<Post> getPosts() {
        return List.of(
        Post.builder()
            .title("Прогулка по парку")
            .image("https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/bbbe4a30-2e49-452f-aaed-a3b4e05843a3/")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build(),
        Post.builder()
            .title("Прогулка по парку")
            .image("https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/bbbe4a30-2e49-452f-aaed-a3b4e05843a3/")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build(),
        Post.builder()
            .title("Прогулка по парку")
            .image("https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/bbbe4a30-2e49-452f-aaed-a3b4e05843a3/")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build());
    }
}
