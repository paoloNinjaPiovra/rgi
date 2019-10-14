package com.task.task.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND, reason = "Task found")
public class TaskFoundException extends Exception {

    public TaskFoundException(String code) {
        super("Task code found : " + code);
    }
}
