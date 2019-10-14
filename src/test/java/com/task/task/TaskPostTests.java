package com.task.task;

import com.task.task.entity.Task;
import com.task.task.enums.Status;
import com.task.task.exception.TaskFoundException;
import com.task.task.exception.TaskNotFoundException;
import com.task.task.exception.UserNotFoundException;
import com.task.task.model.TaskForm;
import com.task.task.model.UserForm;
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

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskPostTests extends Application {

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
    @Transactional
    public void postTest() throws UserNotFoundException, TaskNotFoundException, TaskFoundException {
        List<Task> taskList = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        int oldSize = taskList.size();
        TaskForm taskForm = new TaskForm();
        taskForm.setDescription("task X description");
        taskForm.setName("task X");
        taskForm.setCode("t5be48d5-ae7c-4816-a210-9c984cf760aX");
        UserForm userX = new UserForm("u5be48d5-ae7c-4816-a210-9c984cf760aX", "userX");
        UserForm user4 = new UserForm("u5be48d5-ae7c-4816-a210-9c984cf760a0", "user");
        Set<UserForm> usersForm = new HashSet<>();
        usersForm.add(userX);
        usersForm.add(user4);
        taskForm.setUsers(usersForm);
        Task task = taskService.save("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskForm);
        Assert.assertTrue(task.getCode().equals("t5be48d5-ae7c-4816-a210-9c984cf760aX"));
        Assert.assertTrue(task.getStatus().equals(Status.NEW));
        Assert.assertTrue(task.getDescription().equals("task X description"));
        Assert.assertTrue(task.getName().equals("task X"));
        Assert.assertTrue(task.getUsers().size() == 2);
        taskList = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(taskList.size() == (oldSize + 1));
    }

    @Test(expected = TaskNotFoundException.class)
    public void postTestKoTaskCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760aX";
        taskService.patch("u5be48d5-ae7c-4816-a210-9c984cf760a0", new TaskForm(), taskCodeToPatch);
    }

    @Test(expected = TaskNotFoundException.class)
    public void postTestKoUserCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        taskService.patch("u5be48d5-ae7c-4816-a210-9c984cf760aX", new TaskForm(), taskCodeToPatch);
    }

    @Test(expected = UserNotFoundException.class)
    public void postTestNoUSerCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        taskService.patch("", new TaskForm(), taskCodeToPatch);
    }

}
