package com.rgi.rgi.service;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.enums.Status;
import com.rgi.rgi.exception.TaskFoundException;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskDetail;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.TaskList;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public TaskList list(String userSession) throws UserNotFoundException {
        log.info("taskService: list begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            TaskList taskList = new TaskList(taskRepository.findTask(userSession));
            log.info("taskService: list ... end!");
            return taskList;
        } else {
            log.info("taskService: list ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public TaskDetail get(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        log.info("taskService: get begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = getTask(userSession, taskCode, "get");
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setCode(task.getCode());
            taskDetail.setName(task.getName());
            taskDetail.setDescription(task.getDescription());
            taskDetail.setStatus(task.getStatus());
            taskDetail.setUsers(task.getUsers());
            log.info("taskService: get ... end!");
            return taskDetail;
        } else {
            log.info("taskService: get ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public void delete(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        log.info("taskService: delete begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = getTask(userSession, taskCode, "delete");
            taskRepository.delete(task);
            log.info("taskService: delete ... end!");
        } else {
            log.info("taskService: delete ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task saveOrUpdate(String userSession, TaskDetail newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        log.info("taskService: saveOrUpdate begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = getTask(userSession, taskCode, "saveOrUpdate");
            if (null != task) {
                task.setDescription(newTask.getDescription());
                task.setName(newTask.getName());
                task.setStatus(newTask.getStatus());
                task.setUsers(newTask.getUsers());
                log.info("taskService: saveOrUpdate ... end!");
                return taskRepository.save(task);
            } else {
                task = new Task();
                task.setUsers(newTask.getUsers());
                task.setStatus(newTask.getStatus());
                task.setName(newTask.getName());
                task.setDescription(newTask.getDescription());
                log.info("taskService: saveOrUpdate ... end!");
                return taskRepository.save(task);
            }
        } else {
            log.info("taskService: saveOrUpdate ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task patch(String userSession, Task newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        log.info("taskService: patch begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = getTask(userSession, taskCode, "patch");
            if (null != task) {
                if (!task.getDescription().equalsIgnoreCase(newTask.getDescription()))
                    task.setDescription(newTask.getDescription());
                if (!task.getName().equalsIgnoreCase(newTask.getName()))
                    task.setName(newTask.getName());
                if (!task.getStatus().equals(newTask.getStatus()))
                    task.setStatus(newTask.getStatus());
                if (!task.getUsers().equals(newTask.getUsers()))
                    task.setUsers(newTask.getUsers());
                log.info("taskService: patch ... end!");
                return taskRepository.save(task);
            } else {
                log.info("taskService: patch ...end - no task found!");
                throw new TaskNotFoundException(taskCode);
            }
        } else {
            log.info("taskService: patch ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task save(String userSession, TaskForm newTask) throws UserNotFoundException, TaskFoundException {
        log.info("taskService: save begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = taskRepository.findTaskByCode(newTask.getCode());
            if (null != task) {
                log.info("taskService: save ...end - task " + newTask.getCode() + " found!");
                throw new TaskFoundException(newTask.getCode());
            }
            Set<User> users = newTask.getUsers();
            User user;
            Set<User> associatedUsers = new HashSet<>();
            for(User currentUser: users) {
                user = userRepository.findUserByName(currentUser.getName());
                if (null == user) {
                    user = userRepository.save(new User(currentUser.getName()));
                }
                associatedUsers.add(user);
            }
            task.setUsers(associatedUsers);
            log.info("taskService: save ... end!");
            return taskRepository.save(task);
        } else {
            log.info("taskService: save ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task close(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        log.info("taskService: close begin... ");
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = getTask(userSession, taskCode, "close");
            if (null != task) {
                task.setStatus(Status.CLOSED);
                log.info("taskService: close ... end!");
                return taskRepository.save(task);
            } else {
                log.info("taskService: close ...end - no task found!");
                throw new TaskNotFoundException(taskCode);
            }
        } else {
            log.info("taskService: close ...end - no user found!");
            throw new UserNotFoundException(userSession);
        }
    }

    private Task getTask(String userSession, String taskCode, String methodName) throws TaskNotFoundException {
        if (null == taskRepository.findTaskByCodeAndUserCode(taskCode, userSession)) {
            log.info("taskService: " + methodName + " ...end - no task found!");
            throw new TaskNotFoundException(userSession);
        }
        Task task = taskRepository.findTaskByCode(taskCode);
        if (null == task) {
            log.info("taskService: " + methodName + " ...end - no task found!");
            throw new TaskNotFoundException(userSession);
        }
        return task;
    }
}
