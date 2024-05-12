package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.dto.upload.FileRs;
import ru.inmylife.blog.dto.upload.ImageRs;
import ru.inmylife.blog.service.disk.DiskService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final DiskService diskService;

    @ResponseBody
    @PostMapping(
        value = "upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ImageRs uploadFile(@RequestPart("image") MultipartFile file) {
        return ImageRs.builder()
            .success(1)
            .file(FileRs.builder().url(diskService.uploadAndGetUrl(file)).build())
            .build();
    }
}
