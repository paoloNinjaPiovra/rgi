package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
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
public class TaskListTests {

	@Autowired
	TaskService taskService;

	@Test
	public void listTest() throws UserNotFoundException {
		List<Task> list = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
		Assert.assertTrue(list.size() == 6);
	}

	@Test
	public void listTestKo() throws UserNotFoundException {
		List<Task> list = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760aX");
		Assert.assertTrue(list.isEmpty());
	}
}
