package com.rgi.rgi;

import com.rgi.rgi.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void createUserTest() {
        User user = new User("name");
        Assert.assertTrue(user.getId() == null);
        Assert.assertTrue(StringUtils.isNotEmpty(user.getCode()));
        Assert.assertTrue(user.getTasks().size() == 0);
    }
}
