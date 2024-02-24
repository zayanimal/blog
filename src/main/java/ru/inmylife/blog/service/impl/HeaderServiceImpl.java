package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import ru.inmylife.blog.config.YandexDiskClientProperties;
import ru.inmylife.blog.service.HeaderService;

@RequiredArgsConstructor
public class HeaderServiceImpl implements HeaderService {

    private final YandexDiskClientProperties properties;

    @Override
    public HttpEntity<String> getHttpEntity() {
        return new HttpEntity<>("body", getHttpHeaders());
    }

    @Override
    public HttpEntity<byte[]> getBinaryHttpEntity(byte[] bytes) {
        return new HttpEntity<>(bytes, getHttpHeaders());
    }

    public HttpHeaders getHttpHeaders() {
        val httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, String.format("OAuth %s", properties.getToken()));

        return httpHeaders;
    }
}
