package ru.rabbit.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("disk-client")
public class DiskClientProperties {

    private String url;

    private String token;

    private String path;
}
