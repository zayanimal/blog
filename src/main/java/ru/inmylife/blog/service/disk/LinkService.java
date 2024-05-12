package ru.inmylife.blog.service.disk;

import ru.inmylife.blog.dto.upload.DiskRs;

public interface LinkService {

    String getFileLink(DiskRs diskRs);
}
