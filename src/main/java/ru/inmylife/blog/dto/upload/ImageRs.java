package ru.inmylife.blog.dto.upload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageRs {

    private Integer success;

    private FileRs file;
}
