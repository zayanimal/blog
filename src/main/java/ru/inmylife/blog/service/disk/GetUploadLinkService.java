package ru.inmylife.blog.service.disk;

import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.dto.upload.DiskRs;

public interface GetUploadLinkService {

    DiskRs getUploadLink(MultipartFile file);
}
