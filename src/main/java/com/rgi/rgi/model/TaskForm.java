package com.rgi.rgi.model;

import com.rgi.rgi.entity.User;
import com.rgi.rgi.enums.Status;

import java.util.Set;
import java.util.UUID;

public class TaskForm {

    private String name;
    private String description;
    private Set<UserForm> users;
    private Status status;
    private String code;

    public TaskForm() {
    }

    public TaskForm(String name, String description, Set<UserForm> users) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.status = Status.NEW;
        this.code = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserForm> getUsers() {
        return users;
    }

    public void setUsers(Set<UserForm> users) {
        this.users = users;
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
