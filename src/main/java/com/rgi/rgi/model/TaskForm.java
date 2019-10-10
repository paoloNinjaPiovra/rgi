package com.rgi.rgi.model;

import com.rgi.rgi.entity.User;

import java.util.Set;
import java.util.UUID;

public class TaskForm extends Task {

    public TaskForm() {
    }

    public TaskForm(String name, String description, Set<User> users) {
        super(name, description, users);
    }

}
