package ru.inmylife.blog.dto.block;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class BlockData {

    private String text;

    private String caption;

    private DataFile file;

    private Boolean stretched;

    private Boolean withBackground;

    private Boolean withBorder;
}
