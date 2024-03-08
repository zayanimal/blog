package ru.inmylife.blog.dto.block;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Post {

    private Long id;

    private Long time;

    private String version;

    private List<Block> blocks;

    private Boolean isPublic;
}
