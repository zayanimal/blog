package ru.inmylife.blog.service.impl;

import org.springframework.stereotype.Service;
import ru.inmylife.blog.dto.Post;
import ru.inmylife.blog.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private static final List<Post> POSTS = List.of(
        Post.builder()
            .id(1)
            .title("Прогулка по парку")
            .image("https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/bbbe4a30-2e49-452f-aaed-a3b4e05843a3/")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build(),
        Post.builder()
            .id(2)
            .title("Поход в лес")
            .image("https://hotutent.ru/wp-content/uploads/2017/09/news-190917.jpg")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build(),
        Post.builder()
            .id(3)
            .title("Пробежка по полю")
            .image("https://i.bigenc.ru/resizer/resize?sign=c3XA9HwAMpVrd6MJumt3Kw&filename=vault/d50cbc7dc924a4b832e947b1735a193f.webp&width=1200")
            .text("Когда Илья выходит на свободу, он понимает, что прежняя жизнь, по которой он тосковал, разрушена, и вернуться к ней он больше не сможет. Хотя он не собирался мстить человеку, который отправил его в тюрьму, другого выхода теперь нет.")
            .link("/post")
            .date("2 Февраля 2024")
            .build());

    @Override
    public Post getPostById(Integer id) {
        return POSTS.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Post> getPosts() {
        return POSTS;
    }
}
