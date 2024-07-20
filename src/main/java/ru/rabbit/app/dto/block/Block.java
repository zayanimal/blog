package ru.rabbit.app.dto.block;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@JsonInclude(NON_NULL)
public class Block {

    private String id;

    private String type;

    private BlockData data;
}
