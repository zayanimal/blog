package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import ru.inmylife.blog.config.DiskClientProperties;
import ru.inmylife.blog.service.disk.HeaderService;

@RequiredArgsConstructor
public class HeaderServiceImpl implements HeaderService {

    private final DiskClientProperties properties;

    @Override
    public HttpEntity<String> getHttpEntity() {
        return new HttpEntity<>(getHttpHeaders());
    }

    @Override
    public HttpEntity<byte[]> getBinaryHttpEntity(byte[] bytes) {
        return new HttpEntity<>(bytes, getHttpHeaders());
    }

    @Override
    public HttpHeaders getHttpHeaders() {
        val httpHeaders = new HttpHeaders();
        setHttpHeaders(httpHeaders);

        return httpHeaders;
    }

    @Override
    public void setHttpHeaders(HttpHeaders httpHeaders) {
        httpHeaders.add(HttpHeaders.AUTHORIZATION, String.format("OAuth %s", properties.getToken()));
    }
}
