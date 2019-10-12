package com.rgi.rgi.controller;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskDetail;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.TaskList;

import com.rgi.rgi.service.TaskService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    TaskService taskService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/task")
    public TaskList list(@RequestHeader("user-session") String userSession)
            throws UserNotFoundException {

        log.info("listTask begin...");

        return taskService.list(userSession);
    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/task/{code}")
    public TaskDetail get(@RequestHeader("user-session") String userSession,
                              @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("getTask begin... ");

        return taskService.get(userSession, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/task/{code}")
    public Task delete(@RequestHeader("user-session") String userSession,
                           @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("deleteTask begin... ");

        return taskService.delete(userSession, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/task/{code}")
    public Task saveOrUpdate(@RequestHeader("user-session") String userSession,
                             @RequestBody TaskDetail newTask,
                             @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("saveOrUpdateTask begin... ");

        return taskService.saveOrUpdate(userSession, newTask, taskCode);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/task/{code}")
    public Task patch(@RequestHeader("user-session") String userSession,
                      @RequestBody Task newTask,
                      @PathVariable("code") String taskCode)
            throws UserNotFoundException, TaskNotFoundException {

        log.info("patchTask begin...");

        return taskService.patch(userSession, newTask, taskCode);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/task")
    public Task save(@RequestHeader("user-session") String userSession,
                     @RequestBody TaskForm newTask)
            throws UserNotFoundException, TaskNotFoundException {

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
