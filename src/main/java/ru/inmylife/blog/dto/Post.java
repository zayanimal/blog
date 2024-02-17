package ru.inmylife.blog.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Post {

    private String title;

    private String image;

    private String text;

    private String link;

    private String date;
}
