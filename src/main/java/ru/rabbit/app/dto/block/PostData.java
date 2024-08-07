package ru.rabbit.app.dto.block;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.experimental.Accessors;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
@Accessors(chain = true)
public class PostData {

    private Long id;

    private String username;

    private List<Block> blocks;

    private String topic;

    private String linkText;

    private Boolean isPublic;

    private String date;
}
