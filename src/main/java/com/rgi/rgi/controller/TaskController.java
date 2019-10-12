package com.rgi.rgi.controller;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskDetail;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.TaskList;

import com.rgi.rgi.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/task")
    public TaskList getTaskList(@RequestHeader("user-session") String userSession)
            throws UserNotFoundException {
        return taskService.getTaskList(userSession);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/task/{code}")
    public TaskDetail getTask(@RequestHeader("user-session") String userSession,
                              @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {
        return taskService.getTask(userSession, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/task/{code}")
    public Task deleteTask(@RequestHeader("user-session") String userSession,
                           @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {
        return taskService.deleteTask(userSession, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/task/{code}")
    public Task saveOrUpdate(@RequestHeader("user-session") String userSession,
                             @RequestBody TaskDetail newTask,
                             @PathVariable("code") String taskCode)
            throws UserNotFoundException {
        return taskService.saveOrUpdate(userSession, newTask, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/task/{code}")
    public Task patch(@RequestHeader("user-session") String userSession,
                      @RequestBody Task newTask,
                      @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {
        return taskService.patch(userSession, newTask, taskCode);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    public Task save(@RequestHeader("user-session") String userSession,
                     @RequestBody TaskForm newTask)
            throws UserNotFoundException, TaskNotFoundException {
        return taskService.save(userSession, newTask);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/task/{code}/close")
    public Task closeTask(@RequestHeader("user-session") String userSession,
                          @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {
        return taskService.closeTask(userSession, taskCode);
    }
}
