package ru.inmylife.blog.exception;

public class TitleNotFoundException extends RuntimeException {

    public TitleNotFoundException(String message) {
        super(message);
    }
}
