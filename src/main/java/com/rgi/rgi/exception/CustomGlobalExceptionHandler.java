package com.rgi.rgi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(TaskNotFoundException.class)
    public void springHandleTaskNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public void springHandleUserNotFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value());
    }

    @ResponseBody
    @ExceptionHandler(TaskFoundException.class)
    public void springHandleTaskFound(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.FOUND.value());
    }
}
