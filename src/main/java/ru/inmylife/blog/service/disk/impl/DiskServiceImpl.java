package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.upload.FileRs;
import ru.inmylife.blog.dto.upload.ImageRs;
import ru.inmylife.blog.service.disk.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiskServiceImpl implements DiskService {

    private final GetUploadLinkService uploadLinkService;

    private final UploadService uploadService;

    private final PublishService publishService;

    private final LinkService linkService;

    @Override
    public Mono<ImageRs> uploadAndGetUrl(FilePart file) {
        return uploadLinkService.getUploadLink(file)
            .flatMap(diskRs -> uploadService.upload(diskRs, file))
            .flatMap(publishService::publish)
            .flatMap(linkService::getFileLink)
            .map(res -> ImageRs.builder()
                .success(1)
                .file(FileRs.builder().url(res).build())
                .build());
    }
}
