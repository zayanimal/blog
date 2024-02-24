package ru.inmylife.blog.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.inmylife.blog.service.PublishService;
import ru.inmylife.blog.service.UploadLinkService;
import ru.inmylife.blog.service.UploadService;
import ru.inmylife.blog.service.YandexDiskService;

@Service
@RequiredArgsConstructor
public class YandexDiskServiceImpl implements YandexDiskService {

    private final UploadLinkService uploadLinkService;

    private final UploadService uploadService;


    @Override
    public String uploadAndGetUrl(MultipartFile file) {
        val uploadLink = uploadLinkService.getUploadLink(file);
        uploadService.upload(uploadLink, file);

        return "https://helpx.adobe.com/content/dam/help/en/photoshop/using/convert-color-image-black-white/jcr_content/main-pars/before_and_after/image-before/Landscape-Color.jpg";
    }
}
