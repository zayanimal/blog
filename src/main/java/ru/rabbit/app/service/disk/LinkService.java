package ru.rabbit.app.service.disk;

import ru.rabbit.app.dto.upload.DiskRs;

public interface LinkService {

    String getFileLink(DiskRs diskRs);
}
