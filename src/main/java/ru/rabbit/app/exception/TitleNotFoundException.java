package ru.rabbit.app.exception;

public class TitleNotFoundException extends RuntimeException {

    public TitleNotFoundException(String message) {
        super(message);
    }
}
