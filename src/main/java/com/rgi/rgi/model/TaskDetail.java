package com.rgi.rgi.model;

import com.rgi.rgi.enums.Status;

public class TaskDetail extends AbstractTask {

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
