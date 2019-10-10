package com.rgi.rgi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TaskNotFoundException extends Exception {

    public TaskNotFoundException(String code) {
        super("Task code not found : " + code);
    }
}
