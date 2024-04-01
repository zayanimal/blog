package ru.inmylife.blog.service.disk;

import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.upload.DiskRs;

public interface PublishService {

    Mono<DiskRs> publish(DiskRs diskRs);
}
