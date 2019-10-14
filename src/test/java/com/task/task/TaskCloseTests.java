package com.task.task;

import com.task.task.entity.Task;
import com.task.task.enums.Status;
import com.task.task.exception.TaskNotFoundException;
import com.task.task.exception.UserNotFoundException;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskCloseTests extends Application{

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
