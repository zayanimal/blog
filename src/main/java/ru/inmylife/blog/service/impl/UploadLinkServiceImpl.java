package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.config.YandexDiskClientProperties;
import ru.inmylife.blog.dto.upload.DiskRs;
import ru.inmylife.blog.service.HeaderService;
import ru.inmylife.blog.service.UploadLinkService;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpMethod.GET;

@RequiredArgsConstructor
public class UploadLinkServiceImpl implements UploadLinkService {

    private final RestTemplate restTemplate;

    private final HeaderService headerService;

    private final YandexDiskClientProperties properties;

    @Override
    public DiskRs getUploadLink(MultipartFile file) {
        val path = getUploadPath(file);
        val params = new HashMap<String, String>();
        params.put("path", path);
        params.put("fields", "name");
        params.put("overwrite", "true");

        val response = Optional
            .ofNullable(restTemplate
                .exchange(getUploadUrl(), GET, headerService.getHttpEntity(), DiskRs.class, params)
                .getBody())
            .orElseThrow(() -> new RuntimeException("Ссылка не пришла"));
        response.setPath(path);

        return response;
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

    private String getUploadUrl() {
        return properties.getUrl().concat("/upload?path={path}&fields={fields}&overwrite={overwrite}");
    }
}
