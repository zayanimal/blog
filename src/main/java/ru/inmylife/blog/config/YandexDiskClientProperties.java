package ru.inmylife.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("yandex-disk-client")
public class YandexDiskClientProperties {

    private String url;

    private String token;

    private String path;
}
