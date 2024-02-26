package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;
import ru.inmylife.blog.config.DiskClientProperties;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.PublishService;

import static org.springframework.http.HttpMethod.PUT;

@RequiredArgsConstructor
public class PublishServiceImpl implements PublishService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public void publish(DiskRs diskRs) {
        try {
            restTemplate.exchange(getPublishUrl(diskRs), PUT, headerService.getHttpEntity(), String.class);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось опубликовать файл", e);
        }
    }

    private String getPublishUrl(DiskRs diskRs) {
        return properties.getUrl().concat("/publish?path=").concat(diskRs.getPath());
    }
}
