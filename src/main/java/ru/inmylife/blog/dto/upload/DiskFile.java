package ru.inmylife.blog.dto.upload;

import lombok.Data;

import java.util.List;

@Data
public class DiskFile {

    private String crated;

    private String name;

    private String path;

    private List<Link> sizes;
}
