package ru.rabbit.app.service.disk;

import org.springframework.web.multipart.MultipartFile;

public interface DiskService {

    String uploadAndGetUrl(MultipartFile file);
}
