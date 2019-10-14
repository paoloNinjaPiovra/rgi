package com.task.task;

import com.task.task.entity.Task;
import com.task.task.exception.TaskNotFoundException;
import com.task.task.exception.UserNotFoundException;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskGetTests extends Application {

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
    public void getTest() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToGet = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        Task task = taskService.get("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToGet);
        Assert.assertTrue(task != null);
        Assert.assertTrue(task.getCode().equals(taskCodeToGet));
    }

    @Test(expected = TaskNotFoundException.class)
    public void getTestKoTaskCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760aX";
        taskService.get("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToDelete);

    }

    @Test(expected = TaskNotFoundException.class)
    public void getTestKoUserCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        taskService.get("u5be48d5-ae7c-4816-a210-9c984cf760aX", taskCodeToDelete);
    }

    @Test(expected = UserNotFoundException.class)
    public void getTestNoUSerCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        taskService.get("", taskCodeToDelete);
    }
}
