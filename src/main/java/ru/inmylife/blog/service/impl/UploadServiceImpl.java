package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.HeaderService;
import ru.inmylife.blog.service.UploadService;

import static org.springframework.http.HttpMethod.PUT;

@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    @Override
    @SneakyThrows
    public void upload(DiskRs diskRs, MultipartFile file) {
        val response = restTemplate
            .exchange(diskRs.getHref(), PUT, headerService.getBinaryHttpEntity(file.getBytes()), String.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Не удалось загрузить файл");
        };
    }
}
