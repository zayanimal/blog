package ru.inmylife.blog.service.disk;

import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.dto.upload.DiskRs;

public interface UploadService {

    void upload(DiskRs diskRs, MultipartFile file);
}
