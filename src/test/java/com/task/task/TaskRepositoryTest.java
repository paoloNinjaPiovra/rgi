package com.task.task;

import com.task.task.entity.Task;
import com.task.task.repository.TaskRepository;
import com.task.task.repository.UserRepository;
import org.junit.After;
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
public class TaskRepositoryTest extends Application {

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

    @After
    public void destroy() {
        taskRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void findTaskListUserTest() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(taskList.size() == 6);
    }

    /*@Test
    @Transactional
    public void findTaskListUser1Test() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760a1");
        Assert.assertTrue(taskList.size() == 5);
    }

    @Test
    @Transactional
    public void findTaskListUser2Test() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760a2");
        Assert.assertTrue(taskList.size() == 4);
    }

    @Test
    @Transactional
    public void findTaskListUser3Test() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760a3");
        Assert.assertTrue(taskList.size() == 3);
    }

    @Test
    @Transactional
    public void findTaskListUser4Test() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760a4");
        Assert.assertTrue(taskList.size() == 2);
    }

    @Test
    @Transactional
    public void findTaskListUser5Test() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760a5");
        Assert.assertTrue(taskList.size() == 1);
    }

    @Test
    @Transactional
    public void findTaskListTestKo() {
        List<Task> taskList = taskRepository.findTask("u5be48d5-ae7c-4816-a210-9c984cf760aX");
        Assert.assertTrue(taskList.size() == 0);
    }

    @Test
    @Transactional
    public void findTaskByCodeTest() {
        Task task = taskRepository.findTaskByCode("t5be48d5-ae7c-4816-a210-9c984cf760a0");
        List<User> userList = task.getUsers().stream().collect(Collectors.toList());
        Assert.assertTrue(userList.get(0).getName().equals("user"));
        Assert.assertTrue(userList.get(0).getCode().equals("u5be48d5-ae7c-4816-a210-9c984cf760a0"));
        Assert.assertTrue(task != null);
        Assert.assertTrue(task.getName().equals("task 0"));
        Assert.assertTrue(task.getDescription().equals("task 0 description"));
        Assert.assertTrue(task.getUsers().size() == 1);

    }

    @Test
    @Transactional
    public void findTaskByCodeTestKoForTaskCode() {
        Task task = taskRepository.findTaskByCode("t5be48d5-ae7c-4816-a210-9c984cf760aX");
        Assert.assertTrue(task == null);
    }*/
}
