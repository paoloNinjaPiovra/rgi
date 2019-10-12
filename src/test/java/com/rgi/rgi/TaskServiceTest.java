package com.rgi.rgi;

import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskList;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import com.rgi.rgi.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskServiceTest extends AbstractTaskTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskService taskService;

    @Test
    public void getTaskListTest() throws UserNotFoundException {
        TaskList taskList = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(taskList.getTaskList().size() == 1);
    }

    @Test
    public void getTaskListTestKo() throws UserNotFoundException {
        TaskList taskList = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760aX");
        Assert.assertTrue(taskList.getTaskList().isEmpty());
    }

    @Before
    public void setUp() {
        setUp(userRepository, taskRepository);
    }
}
