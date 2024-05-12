package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.UploadService;

import static org.springframework.http.HttpMethod.PUT;

@Slf4j
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
        }
    }
}
