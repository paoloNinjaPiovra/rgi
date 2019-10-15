package com.task.task.model;

public class UserForm {

    private String code;
    private String name;

    public UserForm(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
}
