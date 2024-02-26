package ru.inmylife.blog.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.config.DiskClientProperties;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.disk.HeaderService;
import ru.inmylife.blog.service.disk.GetUploadLinkService;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
public class GetGetUploadLinkServiceImpl implements GetUploadLinkService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public DiskRs getUploadLink(MultipartFile file) {
        try {
            val path = getUploadPath(file);
            val response = Optional
                .ofNullable(restTemplate
                    .exchange(getUploadUrl(path), GET, headerService.getHttpEntity(), DiskRs.class)
                    .getBody())
                .orElseThrow(() -> new RuntimeException("Ссылка не пришла"));

            response.setPath(path);

            return response;
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить ссылку для загрузки", e);
        }
    }

    private String getUploadPath(MultipartFile file) {
        return properties.getPath()
            .concat(UUID.randomUUID().toString().replace("-", ""))
            .concat(".")
            .concat(getFileExtension(file));
    }

    private String getFileExtension(MultipartFile file) {
        val name = file.getOriginalFilename();

        if (Objects.nonNull(name)) {
            val nameArr = name.split("\\.");
            return nameArr[nameArr.length - 1];
        }

        throw new RuntimeException("Имя файла не найдено");
    }

    private String getUploadUrl(String path) {
        return properties.getUrl()
            .concat("/upload?fields=name&overwrite=true&path=")
            .concat(path);
    }
}
