package ru.inmylife.blog.service.disk;

import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.upload.ImageRs;

public interface DiskService {

    Mono<ImageRs> uploadAndGetUrl(FilePart file);
}
