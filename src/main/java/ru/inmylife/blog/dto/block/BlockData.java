package ru.inmylife.blog.dto.block;

import lombok.Data;

@Data
public class BlockData {

    private String text;

    private String caption;

    private DataFile file;

    private Boolean stretched;

    private Boolean withBackground;

    private Boolean withBorder;
}
