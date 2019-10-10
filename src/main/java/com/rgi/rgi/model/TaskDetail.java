package com.rgi.rgi.model;

import com.rgi.rgi.entity.User;
import com.rgi.rgi.enums.Status;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class TaskDetail extends Task {

    private Status status;

    public TaskDetail() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
