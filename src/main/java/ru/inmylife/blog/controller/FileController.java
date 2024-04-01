package ru.inmylife.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.upload.ImageRs;
import ru.inmylife.blog.service.disk.DiskService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final DiskService diskService;

    @PostMapping(
        value = "upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ImageRs> uploadFile(@RequestPart("image") Mono<FilePart> part) {
        return part.flatMap(diskService::uploadAndGetUrl)
            .doOnError(err -> log.info("Во время загрузки и публикации файла произошла ошибка", err));
    }
}
