package com.foxdev.project.findJobProject.exception;

public class UserAlreadyExistedException extends RuntimeException {
    public UserAlreadyExistedException(String message) {
        super(message);
    }
}
