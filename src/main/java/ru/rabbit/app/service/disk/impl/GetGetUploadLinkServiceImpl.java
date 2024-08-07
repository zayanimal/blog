package ru.rabbit.app.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.rabbit.app.config.DiskClientProperties;
import ru.rabbit.app.dto.upload.DiskRs;
import ru.rabbit.app.service.disk.HeaderService;
import ru.rabbit.app.service.disk.GetUploadLinkService;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@RequiredArgsConstructor
public class GetGetUploadLinkServiceImpl implements GetUploadLinkService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    private final DiskClientProperties properties;

    @Override
    public DiskRs getUploadLink(MultipartFile file) {
        try {
            log.info("Получаем ссылку для загрузки файла");

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
