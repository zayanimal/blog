package ru.inmylife.blog.dto.upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DiskRs {

    @JsonProperty("operation_id")
    private String operationId;

    private String href;

    private String method;

    private String path;

    private Boolean templated;
}
