package ru.rabbit.app.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import ru.rabbit.app.config.DiskClientProperties;
import ru.rabbit.app.dto.upload.DiskRs;
import ru.rabbit.app.service.disk.HeaderService;
import ru.rabbit.app.service.disk.PublishService;

import static org.springframework.http.HttpMethod.PUT;

@Slf4j
@RequiredArgsConstructor
public class PublishServiceImpl implements PublishService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public void publish(DiskRs diskRs) {
        try {
            log.info("Открываем публичный доступ для загруженного файла");

            restTemplate.exchange(getPublishUrl(diskRs), PUT, headerService.getHttpEntity(), String.class);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось опубликовать файл", e);
        }
    }

    private String getPublishUrl(DiskRs diskRs) {
        return properties.getUrl().concat("/publish?path=").concat(diskRs.getPath());
    }
}
