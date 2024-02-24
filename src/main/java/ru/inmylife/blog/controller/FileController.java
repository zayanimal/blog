package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.dto.upload.FileRs;
import ru.inmylife.blog.dto.upload.ImageRs;
import ru.inmylife.blog.service.YandexDiskService;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final YandexDiskService yandexDiskService;

    @ResponseBody
    @PostMapping(
        value = "upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageRs uploadFile(@RequestParam("image") MultipartFile file) {
        return ImageRs.builder()
            .success(1)
            .file(FileRs.builder().url(yandexDiskService.uploadAndGetUrl(file)).build())
            .build();
    }
}
