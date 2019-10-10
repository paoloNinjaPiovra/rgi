package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.enums.Status;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskTest {

    @Test
    public void createTaskTest() {
        Task task = new Task("name", "description");
        Assert.assertTrue(task.getId() == null);
        Assert.assertTrue(StringUtils.isNotEmpty(task.getCode()));
        Assert.assertTrue(task.getStatus().equals(Status.NEW));
        Assert.assertTrue(task.getUsers().size() == 0);
    }
}
