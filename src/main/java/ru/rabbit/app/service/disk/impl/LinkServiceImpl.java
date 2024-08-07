package ru.rabbit.app.service.disk.impl;

import lombok.RequiredArgsConstructor;;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import ru.rabbit.app.config.DiskClientProperties;
import ru.rabbit.app.dto.upload.DiskFile;
import ru.rabbit.app.dto.upload.DiskRs;
import ru.rabbit.app.dto.upload.Link;
import ru.rabbit.app.service.disk.HeaderService;
import ru.rabbit.app.service.disk.LinkService;

import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public String getFileLink(DiskRs diskRs) {
        try {
            log.info("Получаем публичную ссылку на файл");

            // Необходимо, чтобы яндекс успел выдать ссылку
            Thread.sleep(500);

            val response = Optional.ofNullable(restTemplate
                    .exchange(getResourceUrl(diskRs), GET, headerService.getHttpEntity(), DiskFile.class)
                    .getBody())
                .orElseThrow(() -> new RuntimeException("Пустое тело"));

            return response.getSizes().stream()
                .filter(link -> link.getName().equals("XL"))
                .findFirst()
                .map(Link::getUrl)
                .orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Не удалолось получить ресурсную ссылку", e);
        }
    }

    private String getResourceUrl(DiskRs diskRs) {
        return properties.getUrl().concat("?path=").concat(diskRs.getPath());
    }
}
