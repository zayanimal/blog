package ru.rabbit.app.service.disk.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.rabbit.app.service.disk.DiskService;
import ru.rabbit.app.service.disk.GetUploadLinkService;
import ru.rabbit.app.service.disk.LinkService;
import ru.rabbit.app.service.disk.PublishService;
import ru.rabbit.app.service.disk.UploadService;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiskServiceImpl implements DiskService {

    private final GetUploadLinkService uploadLinkService;

    private final UploadService uploadService;

    private final PublishService publishService;

    private final LinkService linkService;

    @Override
    public String uploadAndGetUrl(MultipartFile file) {
        try {
            val uploadLinkRs = uploadLinkService.getUploadLink(file);

            uploadService.upload(uploadLinkRs, file);
            publishService.publish(uploadLinkRs);

            return linkService.getFileLink(uploadLinkRs);
        } catch (Exception e) {
            throw new RuntimeException("Произошла ошибка транзакции загрузки файла", e);
        }
    }
}
