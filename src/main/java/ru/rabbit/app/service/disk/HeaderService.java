package ru.rabbit.app.service.disk;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public interface HeaderService {
    HttpEntity<String> getHttpEntity();

    HttpEntity<byte[]> getBinaryHttpEntity(byte[] bytes);

    HttpHeaders getHttpHeaders();

    void setHttpHeaders(HttpHeaders httpHeaders);
}
