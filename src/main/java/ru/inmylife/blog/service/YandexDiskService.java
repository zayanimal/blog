package ru.inmylife.blog.service;

import org.springframework.web.multipart.MultipartFile;

public interface YandexDiskService {

    String uploadAndGetUrl(MultipartFile file);
}
