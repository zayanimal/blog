package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.UploadService;

@Slf4j
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final HeaderService headerService;

    @Override
    public Mono<DiskRs> upload(DiskRs diskRs, FilePart file) {
        log.info("Загружаем файл по ссылке");
        return mergeDataBuffers(file.content())
            .flatMap(bytes -> WebClient.create()
                .put()
                .uri(diskRs.getHref())
                .headers(headerService::setHttpHeaders)
                .body(BodyInserters.fromValue(bytes))
                .retrieve()
                .bodyToMono(String.class))
            .thenReturn(diskRs);
    }

    private Mono<byte[]> mergeDataBuffers(Flux<DataBuffer> dataBufferFlux) {
        return DataBufferUtils.join(dataBufferFlux)
            .map(dataBuffer -> {
                byte[] bytes = new byte[dataBuffer.readableByteCount()];
                dataBuffer.read(bytes);
                DataBufferUtils.release(dataBuffer);
                return bytes;
            });
    }
}
