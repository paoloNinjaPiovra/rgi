package com.task.task.model;

import java.util.Set;

public class TaskForm extends Task4List {

    private String description;
    private Set<UserForm> users;

    public TaskForm() {
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
}
