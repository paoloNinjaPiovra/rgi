package com.rgi.rgi;

import com.rgi.rgi.entity.User;
import com.rgi.rgi.util.SetUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

    @Test
    public void matchUserSet() {

        User user = new User("user");
        user.setCode("u5be48d5-ae7c-4816-a210-9c984cf760a0");
        User user1 = new User("user1");
        user1.setCode("u5be48d5-ae7c-4816-a210-9c984cf760a1");
        User user2 = new User("user2");
        user2.setCode("u5be48d5-ae7c-4816-a210-9c984cf760a2");
        User user3 = new User("user3");
        user3.setCode("u5be48d5-ae7c-4816-a210-9c984cf760a3");
        User user4 = new User("user4");
        user4.setCode("u5be48d5-ae7c-4816-a210-9c984cf760a4");
        User user5 = new User("user5");
        user5.setCode("u5be48d5-ae7c-4816-a210-9c984cf760a5");

        Set<User> userSet4 = new HashSet<>();
        userSet4.add(user);
        userSet4.add(user1);
        userSet4.add(user2);
        userSet4.add(user3);
        userSet4.add(user4);
        Set<User> userSet5 = new HashSet<>();
        userSet5.add(user);
        userSet5.add(user1);
        userSet5.add(user2);
        userSet5.add(user3);
        userSet5.add(user4);
        userSet5.add(user5);
        Set<User> userSet6 = new HashSet<>();
        userSet6.add(user);
        userSet6.add(user1);
        userSet6.add(user2);
        userSet6.add(user3);
        userSet6.add(user4);
        userSet6.add(user5);

        Assert.assertTrue(SetUtil.equals(userSet4, userSet5) == false);
        Assert.assertTrue(SetUtil.equals(userSet4, userSet6) == false);
        Assert.assertTrue(SetUtil.equals(userSet6, userSet5) == true);

    }
}
