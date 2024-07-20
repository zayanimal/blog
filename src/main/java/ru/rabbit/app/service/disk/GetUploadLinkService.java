package ru.rabbit.app.service.disk;

import org.springframework.web.multipart.MultipartFile;
import ru.rabbit.app.dto.upload.DiskRs;

public interface GetUploadLinkService {

    DiskRs getUploadLink(MultipartFile file);
}
