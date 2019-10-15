package com.task.task;

import com.task.task.exception.UserNotFoundException;
import com.task.task.model.Task4List;
import com.task.task.repository.TaskRepository;
import com.task.task.repository.UserRepository;
import com.task.task.service.TaskService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskListTests extends Application {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
        super.setUp(userRepository, taskRepository);
    }

    @Test
    public void listTest() throws UserNotFoundException {
        List<Task4List> list = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(list.size() == 6);
    }

    @Test
    public void listTestKo() throws UserNotFoundException {
        List<Task4List> list = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760aX");
        Assert.assertTrue(list.isEmpty());
    }
}
