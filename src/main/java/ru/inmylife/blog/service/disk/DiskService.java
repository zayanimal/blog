package ru.inmylife.blog.service.disk;

import org.springframework.web.multipart.MultipartFile;

public interface DiskService {

    String uploadAndGetUrl(MultipartFile file);
}
