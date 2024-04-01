package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.config.DiskClientProperties;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.GetUploadLinkService;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class GetGetUploadLinkServiceImpl implements GetUploadLinkService {

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public Mono<DiskRs> getUploadLink(FilePart file) {
        log.info("Получаем ссылку для загрузки файла");
        val path = getUploadPath(file);
        return WebClient.create()
            .get()
            .uri(getUploadUrl(path))
            .headers(headerService::setHttpHeaders)
            .retrieve()
            .bodyToMono(DiskRs.class)
            .map(res -> {
                res.setPath(path);
                return res;
            });
    }

    private String getUploadPath(FilePart file) {
        return properties.getPath()
            .concat(UUID.randomUUID().toString().replace("-", ""))
            .concat(".")
            .concat(getFileExtension(file));
    }

    private String getFileExtension(FilePart file) {
        val name = file.filename();
        val nameArr = name.split("\\.");
        return nameArr[nameArr.length - 1];
    }

    private String getUploadUrl(String path) {
        return properties.getUrl()
            .concat("/upload?fields=name&overwrite=true&path=")
            .concat(path);
    }
}
