package com.example.demo.application.exception;

public class CustomException extends Exception {
    private static final long serialVersionUID = 1L;

    private Integer value;

    public CustomException(String message, Integer value) {
        super(message);
        this.value = value;
    }

    public Integer getValue () {
        return value;
    }
}
