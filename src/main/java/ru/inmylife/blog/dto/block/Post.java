package ru.inmylife.blog.dto.block;

import lombok.Data;

import java.util.List;

@Data
public class Post {

    private Long time;

    private String version;

    private List<Block> blocks;
}
