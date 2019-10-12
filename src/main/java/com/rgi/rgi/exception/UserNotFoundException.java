package com.rgi.rgi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NON_AUTHORITATIVE_INFORMATION, reason = "User session not found")
public class UserNotFoundException extends Exception {

    public UserNotFoundException(String code) {
        super("User code not found : " + code);
    }
}
