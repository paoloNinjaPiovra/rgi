package com.rgi.rgi.service;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.enums.Status;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskDetail;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.TaskList;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public TaskList getTaskList(String userSession) throws UserNotFoundException {
        if (StringUtils.isNotEmpty(userSession)) {
            TaskList taskList = new TaskList(taskRepository.findTask(userSession));
            return taskList;
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, readOnly = true, isolation = Isolation.READ_COMMITTED)
    public TaskDetail getTask(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = taskRepository.findTaskByCode(taskCode, userSession);
            if (null == task)
                throw new TaskNotFoundException(userSession);
            Set<User> users = new HashSet<>(userRepository.findUserByTaskCode(taskCode, userSession));
            TaskDetail taskDetail = new TaskDetail();
            taskDetail.setCode(task.getCode());
            taskDetail.setName(task.getName());
            taskDetail.setDescription(task.getDescription());
            taskDetail.setStatus(task.getStatus());
            taskDetail.setUsers(users);
            return taskDetail;
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task deleteTask(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = taskRepository.findTaskByCode(taskCode, userSession);
            if (null == task)
                throw new TaskNotFoundException(task.getCode());
            taskRepository.delete(task);
            return task;
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task saveOrUpdate(String userSession, TaskDetail newTask, String taskCode) throws UserNotFoundException {
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = taskRepository.findTaskByCode(taskCode, userSession);
            if (null != task) {
                task.setDescription(newTask.getDescription());
                task.setName(newTask.getName());
                task.setStatus(newTask.getStatus());
                task.setUsers(newTask.getUsers());
                return taskRepository.save(task);
            } else {
                task = new Task();
                task.setUsers(newTask.getUsers());
                task.setStatus(newTask.getStatus());
                task.setName(newTask.getName());
                task.setDescription(newTask.getDescription());
                return taskRepository.save(task);
            }
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task patch(String userSession, Task newTask, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = taskRepository.findTaskByCode(taskCode, userSession);
            if (null != task) {
                if (!task.getDescription().equalsIgnoreCase(newTask.getDescription()))
                    task.setDescription(newTask.getDescription());
                if (!task.getName().equalsIgnoreCase(newTask.getName()))
                    task.setName(newTask.getName());
                if (!task.getStatus().equals(newTask.getStatus()))
                    task.setStatus(newTask.getStatus());
                if (!task.getUsers().equals(newTask.getUsers()))
                    task.setUsers(newTask.getUsers());
                return taskRepository.save(task);
            } else {
                throw new TaskNotFoundException(taskCode);
            }
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task save(String userSession, TaskForm newTask) throws UserNotFoundException, TaskNotFoundException {
        if (StringUtils.isNotEmpty(userSession) && userSession.equalsIgnoreCase("user-session")) {
            if (null != taskRepository.findTaskByCode(newTask.getCode(), userSession))
                throw new TaskNotFoundException(userSession);
            Task task = new Task(newTask.getName(), newTask.getDescription());
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
            return taskRepository.save(task);
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

    @Override
    @Transactional(rollbackFor = { UserNotFoundException.class, TaskNotFoundException.class }, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public Task closeTask(String userSession, String taskCode) throws UserNotFoundException, TaskNotFoundException {
        if (StringUtils.isNotEmpty(userSession)) {
            Task task = taskRepository.findTaskByCode(taskCode, userSession);
            if (null != task) {
                task.setStatus(Status.CLOSED);
                return taskRepository.save(task);
            } else {
                throw new TaskNotFoundException(taskCode);
            }
        } else {
            throw new UserNotFoundException(userSession);
        }
    }

}
