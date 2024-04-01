package ru.inmylife.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.inmylife.blog.service.disk.*;
import ru.inmylife.blog.service.disk.impl.*;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({ DiskClientProperties.class })
public class DiskClientConfiguration {

    private final DiskClientProperties properties;

    @Bean
    public GetUploadLinkService uploadLinkService() {
        return new GetGetUploadLinkServiceImpl(headerService(), properties);
    }

    @Bean
    public UploadService uploadService() {
        return new UploadServiceImpl(headerService());
    }

    @Bean
    public PublishService publishService() {
        return new PublishServiceImpl(headerService(), properties);
    }

    @Bean
    public LinkService linkService() {
        return new LinkServiceImpl(headerService(), properties);
    }

    @Bean
    public HeaderService headerService() {
        return new HeaderServiceImpl(properties);
    }
}
