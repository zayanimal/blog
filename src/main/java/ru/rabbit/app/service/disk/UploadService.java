package ru.rabbit.app.service.disk;

import org.springframework.web.multipart.MultipartFile;
import ru.rabbit.app.dto.upload.DiskRs;

public interface UploadService {

    void upload(DiskRs diskRs, MultipartFile file);
}
