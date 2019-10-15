package com.task.task.service;

import com.task.task.entity.Task;
import com.task.task.exception.TaskFoundException;
import com.task.task.exception.TaskNotFoundException;
import com.task.task.exception.UserNotFoundException;
import com.task.task.model.Task4List;
import com.task.task.model.TaskForm;

import java.util.List;

public interface TaskService {

    List<Task4List> list(String userSession) throws UserNotFoundException;
    Task get(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    void delete(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task patch(String userSession, TaskForm newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task save(String userSession, TaskForm newTask) throws UserNotFoundException, TaskNotFoundException, TaskFoundException;
    Task close(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
}
