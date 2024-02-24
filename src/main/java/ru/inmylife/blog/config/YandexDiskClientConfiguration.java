package ru.inmylife.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import ru.inmylife.blog.service.HeaderService;
import ru.inmylife.blog.service.UploadLinkService;
import ru.inmylife.blog.service.UploadService;
import ru.inmylife.blog.service.impl.HeaderServiceImpl;
import ru.inmylife.blog.service.impl.UploadLinkServiceImpl;
import ru.inmylife.blog.service.impl.UploadServiceImpl;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({ YandexDiskClientProperties.class })
public class YandexDiskClientConfiguration {

    private final YandexDiskClientProperties properties;

    @Bean
    public UploadLinkService uploadLinkService() {
        return new UploadLinkServiceImpl(restTemplate(), headerService(), properties);
    }

    @Bean
    public UploadService uploadService() {
        return new UploadServiceImpl(restTemplate(), headerService());
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
