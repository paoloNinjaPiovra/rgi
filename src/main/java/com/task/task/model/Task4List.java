package com.task.task.model;

import com.task.task.enums.Status;

public class Task4List {

    private String name;
    private Status status;
    private String code;

    public Task4List(String name, Status status, String code) {
        this.name = name;
        this.status = status;
        this.code = code;
    }

    public Task4List() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
