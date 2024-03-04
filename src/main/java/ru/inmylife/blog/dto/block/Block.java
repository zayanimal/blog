package ru.inmylife.blog.dto.block;

import lombok.Data;

@Data
public class Block {

    private String id;

    private String type;

    private BlockData data;
}
