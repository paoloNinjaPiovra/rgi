package com.rgi.rgi.model;

import com.rgi.rgi.entity.User;

import java.util.Set;

public class TaskForm extends AbstractTask {

    public TaskForm() {
    }

    public TaskForm(String name, String description, Set<User> users) {
        super(name, description, users);
    }

}
