package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.config.DiskClientProperties;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.PublishService;

@Slf4j
@RequiredArgsConstructor
public class PublishServiceImpl implements PublishService {

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public Mono<DiskRs> publish(DiskRs diskRs) {
        log.info("Открываем публичный доступ для загруженного файла");

        return WebClient.create()
            .put()
            .uri(getPublishUrl(diskRs))
            .headers(headerService::setHttpHeaders)
            .retrieve()
            .bodyToMono(String.class)
            .thenReturn(diskRs);
    }

    private String getPublishUrl(DiskRs diskRs) {
        return properties.getUrl().concat("/publish?path=").concat(diskRs.getPath());
    }
}
