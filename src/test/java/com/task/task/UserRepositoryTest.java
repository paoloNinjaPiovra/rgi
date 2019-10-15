package com.task.task;

import com.task.task.entity.User;
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
public class UserRepositoryTest extends Application {

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
    public void findUserByNameTest() {
        User user = userRepository.findUserByName("user");
        Assert.assertTrue(user != null);
        Assert.assertTrue(user.getName().equals("user"));
        Assert.assertTrue(user.getCode().equals("u5be48d5-ae7c-4816-a210-9c984cf760a0"));
    }

    @Test
    public void findUserByNameTestKo() {
        User user = userRepository.findUserByName("fakeUser");
        Assert.assertTrue(user == null);
    }

    @Test
    public void findUserByUserCodeTest() {
        User user = userRepository.findUserByUserCode("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(user != null);
    }

    @Test
    public void findUserByUserCodeTestKo() {
        User user = userRepository.findUserByUserCode("fakeUser");
        Assert.assertTrue(user == null);
    }

    @Test
    public void findUserByTaskCodeTest() {
        List<User> userList = userRepository.findUserByTaskCode("t5be48d5-ae7c-4816-a210-9c984cf760a0", "u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(userList.size() == 1);
        Assert.assertTrue(userList.get(0).getCode().equals("u5be48d5-ae7c-4816-a210-9c984cf760a0"));
        Assert.assertTrue(userList.get(0).getName().equals("user"));
    }

    @Test
    public void findUserByTaskCodeTestKoForTaskCode() {
        List<User> userList = userRepository.findUserByTaskCode("t5be48d5-ae7c-4816-a210-9c984cf760aX", "u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(userList.isEmpty());

    }

    @Test
    public void findUserByTaskCodeTestKoforUserCode() {
        List<User> userList = userRepository.findUserByTaskCode("t5be48d5-ae7c-4816-a210-9c984cf760a0", "u5be48d5-ae7c-4816-a210-9c984cf760aX");
        Assert.assertTrue(userList.isEmpty());
    }
}
