package ru.inmylife.blog.service;

import ru.inmylife.blog.dto.upload.DiskRs;

public interface PublishService {

    DiskRs publish(String path);
}
