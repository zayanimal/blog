package ru.inmylife.blog.service.disk;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.upload.DiskRs;

public interface UploadService {

    Mono<DiskRs> upload(DiskRs diskRs, FilePart file);
}
