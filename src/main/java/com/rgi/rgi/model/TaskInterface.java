package com.rgi.rgi.model;

import com.rgi.rgi.entity.User;

import java.util.Set;

public interface TaskInterface {
    Set<User> getUsers();
    String getName();
    String getDescription();
}
