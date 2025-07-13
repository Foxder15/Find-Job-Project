package com.foxdev.project.findJobProject.exception;

public class CompanyAlreadyExistedException extends RuntimeException {
    public CompanyAlreadyExistedException(String message) {
        super(message);
    }
}
