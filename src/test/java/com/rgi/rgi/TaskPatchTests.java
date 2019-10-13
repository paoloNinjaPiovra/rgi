package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.enums.Status;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.model.TaskForm;
import com.rgi.rgi.model.UserForm;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import com.rgi.rgi.service.TaskService;
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

import static java.util.stream.Collectors.toList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskPatchTests extends Application {

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
    public void patchTest() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        Task task = taskService.get("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToPatch);
        Task originalTask = task;
        Assert.assertTrue(task.getName() == "task 0");
        Assert.assertTrue(task.getDescription() == "task 0 description");
        Assert.assertTrue(task.getStatus().equals(Status.NEW));
        List<User> users = task.getUsers().stream().collect(toList());
        Assert.assertTrue(users.size() == 1);
        Assert.assertTrue(users.get(0).getCode().equals("u5be48d5-ae7c-4816-a210-9c984cf760a0"));
        Assert.assertTrue(users.get(0).getName().equals("user"));

        TaskForm taskForm = new TaskForm();
        taskForm.setDescription("task X description");
        taskForm.setName("task X");
        taskForm.setStatus(Status.RUNNING);
        UserForm userX = new UserForm("u5be48d5-ae7c-4816-a210-9c984cf760aX", "userX");
        Set<UserForm> usersForm = new HashSet<>();
        usersForm.add(userX);
        UserForm userFormToAdd;
        for (User currentUser : task.getUsers()) {
            userFormToAdd = new UserForm(currentUser.getCode(), currentUser.getName());
            usersForm.add(userFormToAdd);
        }
        taskForm.setUsers(usersForm);
        task = taskService.patch("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskForm, taskCodeToPatch);
        Assert.assertTrue(task.getCode().equals(originalTask.getCode()));
        Assert.assertTrue(task.getStatus().equals(Status.RUNNING));
        Assert.assertTrue(task.getDescription().equals("task X description"));
        Assert.assertTrue(task.getName().equals("task X"));
        Assert.assertTrue(task.getUsers().size() == 2);
    }

    @Test(expected = TaskNotFoundException.class)
    public void patchTestKoTaskCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760aX";
        taskService.patch("u5be48d5-ae7c-4816-a210-9c984cf760a0", new TaskForm(), taskCodeToPatch);
    }

    @Test(expected = TaskNotFoundException.class)
    public void patchTestKoUserCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        taskService.patch("u5be48d5-ae7c-4816-a210-9c984cf760aX", new TaskForm(), taskCodeToPatch);
    }

    @Test(expected = UserNotFoundException.class)
    public void patchTestNoUSerCode() throws UserNotFoundException, TaskNotFoundException {
        String taskCodeToPatch = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
        taskService.patch("", new TaskForm(), taskCodeToPatch);
    }
}
