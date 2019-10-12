package com.rgi.rgi.model;

import com.rgi.rgi.entity.User;

import java.util.Set;
import java.util.UUID;

public abstract class AbstractTask implements TaskInterface {

    private String name;
    private String description;
    private Set<User> users;
    private String code;

    public AbstractTask() {
    }

    public AbstractTask(String name, String description, Set<User> users) {
        this.name = name;
        this.description = description;
        this.users = users;
        this.code = UUID.randomUUID().toString();
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
