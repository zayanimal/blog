package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.config.DiskClientProperties;
import ru.inmylife.blog.dto.upload.DiskFile;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.dto.upload.Link;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.LinkService;

@Slf4j
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public Mono<String> getFileLink(DiskRs diskRs) {
        log.info("Получаем публичную ссылку на файл");
        return WebClient.create()
            .get()
            .uri(getResourceUrl(diskRs))
            .headers(headerService::setHttpHeaders)
            .exchangeToMono(resp -> resp.bodyToMono(DiskFile.class))
            .flatMapIterable(DiskFile::getSizes)
            .filter(link -> link.getName().equals("XL"))
            .take(1)
            .next()
            .map(Link::getUrl);
    }

    private String getResourceUrl(DiskRs diskRs) {
        return properties.getUrl().concat("?path=").concat(diskRs.getPath());
    }
}
