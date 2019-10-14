package com.task.task.controller;

import com.task.task.entity.Task;
import com.task.task.exception.TaskFoundException;
import com.task.task.exception.TaskNotFoundException;
import com.task.task.exception.UserNotFoundException;
import com.task.task.model.TaskForm;

import com.task.task.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/task")
    public List<Task> list(@RequestHeader("user-session") String userSession)
            throws UserNotFoundException {

        log.info("listTask begin...");

        return taskService.list(userSession);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/task/{code}")
    public Task get(@RequestHeader("user-session") String userSession,
                    @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("getTask begin... ");

        return taskService.get(userSession, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/task/{code}")
    public void delete(@RequestHeader("user-session") String userSession,
                           @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("deleteTask begin... ");

        taskService.delete(userSession, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/task/{code}")
    public Task patch(@RequestHeader("user-session") String userSession,
                      @RequestBody TaskForm newTask,
                      @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("patchTask begin...");

        return taskService.patch(userSession, newTask, taskCode);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    public Task save(@RequestHeader("user-session") String userSession,
                     @RequestBody TaskForm newTask)
            throws UserNotFoundException, TaskNotFoundException, TaskFoundException {

        log.info("saveTask begin... ");

        return taskService.save(userSession, newTask);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/task/{code}/close")
    public Task close(@RequestHeader("user-session") String userSession,
                      @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("closeTask begin... ");

        return taskService.close(userSession, taskCode);
    }
}
