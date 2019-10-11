package com.rgi.rgi.service;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskDetail;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.TaskList;

public interface TaskService {
    TaskList getTaskList(String userSession) throws UserNotFoundException;
    TaskDetail getTask(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task deleteTask(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task saveOrUpdate(String userSession, TaskDetail newTask, String taskCode) throws UserNotFoundException;
    Task patch(String userSession, Task newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task save(String userSession, TaskForm newTask) throws UserNotFoundException, TaskNotFoundException;
    Task closeTask(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
}
