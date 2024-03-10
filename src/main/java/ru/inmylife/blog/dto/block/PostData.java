package ru.inmylife.blog.dto.block;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class PostData {

    private Long id;

    private List<Block> blocks;

    private Set<String> topics;

    private Boolean isPublic;

    private String date;
}
