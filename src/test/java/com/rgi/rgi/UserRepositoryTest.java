package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
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
public class UserRepositoryTest extends AbstractTaskTest {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    @Transactional
    public void findUserByNameTest() {
        User user = userRepository.findUserByName("user");
        Assert.assertTrue(user != null);
        Assert.assertTrue(user.getName().equals("user"));
        Assert.assertTrue(user.getCode().equals("u5be48d5-ae7c-4816-a210-9c984cf760a0"));
    }

    @Test
    @Transactional
    public void findUserByNameTestKo() {
        User user = userRepository.findUserByName("fakeUser");
        Assert.assertTrue(user == null);
    }

    @Test
    @Transactional
    public void findUserByUserCodeTest() {
        User user = userRepository.findUserByUserCode("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(user != null);
    }

    @Test
    @Transactional
    public void findUserByUserCodeTestKo() {
        User user = userRepository.findUserByUserCode("fakeUser");
        Assert.assertTrue(user == null);
    }

    @Test
    @Transactional
    public void findUserByTaskCodeTest() {
        List<User> userList = userRepository.findUserByTaskCode ("t5be48d5-ae7c-4816-a210-9c984cf760a0","u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(userList.size() == 1);
        Assert.assertTrue(userList.get(0).getCode().equals("u5be48d5-ae7c-4816-a210-9c984cf760a0"));
        Assert.assertTrue(userList.get(0).getName().equals("user"));
    }

    @Test
    @Transactional
    public void findUserByTaskCodeTestKoForTaskCode() {
        List<User> userList = userRepository.findUserByTaskCode ("t5be48d5-ae7c-4816-a210-9c984cf760aX","u5be48d5-ae7c-4816-a210-9c984cf760a0");
        Assert.assertTrue(userList.isEmpty());

    }

    @Test
    @Transactional
    public void findUserByTaskCodeTestKoforUserCode() {
        List<User> userList = userRepository.findUserByTaskCode ("t5be48d5-ae7c-4816-a210-9c984cf760a0","u5be48d5-ae7c-4816-a210-9c984cf760aX");
        Assert.assertTrue(userList.isEmpty());
    }

    @Before
    public void setUp() {
        super.setUp(userRepository, taskRepository);
    }
}
