package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskGetTests {

    @Autowired
    TaskService taskService;

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
