package com.rgi.rgi.service;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.exception.TaskFoundException;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskDetail;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.TaskList;

public interface TaskService {
    TaskList list(String userSession) throws UserNotFoundException;
    TaskDetail get(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    void delete(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task saveOrUpdate(String userSession, TaskDetail newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task patch(String userSession, Task newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException;
    Task save(String userSession, TaskForm newTask) throws UserNotFoundException, TaskNotFoundException, TaskFoundException;
    Task close(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException;
}
