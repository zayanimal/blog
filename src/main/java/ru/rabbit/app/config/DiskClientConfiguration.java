package ru.rabbit.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.rabbit.app.service.disk.GetUploadLinkService;
import ru.rabbit.app.service.disk.HeaderService;
import ru.rabbit.app.service.disk.LinkService;
import ru.rabbit.app.service.disk.PublishService;
import ru.rabbit.app.service.disk.UploadService;
import ru.rabbit.app.service.disk.impl.GetGetUploadLinkServiceImpl;
import ru.rabbit.app.service.disk.impl.HeaderServiceImpl;
import ru.rabbit.app.service.disk.impl.LinkServiceImpl;
import ru.rabbit.app.service.disk.impl.PublishServiceImpl;
import ru.rabbit.app.service.disk.impl.UploadServiceImpl;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({ DiskClientProperties.class })
public class DiskClientConfiguration {

    private final DiskClientProperties properties;

    @Bean
    public GetUploadLinkService uploadLinkService() {
        return new GetGetUploadLinkServiceImpl(restTemplate(), headerService(), properties);
    }

    @Bean
    public UploadService uploadService() {
        return new UploadServiceImpl(restTemplate(), headerService());
    }

    @Bean
    public PublishService publishService() {
        return new PublishServiceImpl(restTemplate(), headerService(), properties);
    }

    @Bean
    public LinkService linkService() {
        return new LinkServiceImpl(restTemplate(), headerService(), properties);
    }

    @Bean
    public HeaderService headerService() {
        return new HeaderServiceImpl(properties);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
