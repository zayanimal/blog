package ru.rabbit.app.exception;

import lombok.Getter;

@Getter
public class DiskException extends RuntimeException {

    private final String fileName;

    public DiskException(String message, String fileName, Throwable cause) {
        super(message, cause);
        this.fileName = fileName;
    }
}
