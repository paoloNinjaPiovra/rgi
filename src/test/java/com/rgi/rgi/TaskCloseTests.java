package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.enums.Status;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
import com.rgi.rgi.service.TaskService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskCloseTests {

	@Autowired
	TaskService taskService;

	@Test
	public void closeTest() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToClose = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
		Task task = taskService.get("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToClose);
		Assert.assertTrue(task.getStatus().equals(Status.NEW));
		task = taskService.close("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToClose);
		Assert.assertTrue(task.getStatus().equals(Status.CLOSED));
	}

	@Test(expected = TaskNotFoundException.class)
	public void closeTestKoTaskCode() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToClose = "t5be48d5-ae7c-4816-a210-9c984cf760aX";
		taskService.delete("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToClose);

	}

	@Test(expected = TaskNotFoundException.class)
	public void closeTestKoUserCode() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToClose = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
		taskService.close("u5be48d5-ae7c-4816-a210-9c984cf760aX", taskCodeToClose);
	}

	@Test(expected = UserNotFoundException.class)
	public void closeTestNoUSerCode() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToClose = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
		taskService.close("", taskCodeToClose);
	}
}
