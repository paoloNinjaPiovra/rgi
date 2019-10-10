package com.rgi.rgi.model;

import com.rgi.rgi.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        if (null == taskList)
            this.taskList = new ArrayList<>();
        else
            this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
}
