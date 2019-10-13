package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.exception.TaskNotFoundException;
import com.rgi.rgi.exception.UserNotFoundException;
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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskDeleteTests extends Application {

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
	public void deleteTest() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
		List<Task> list = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
		Assert.assertTrue(list.size() == 6);
		taskService.delete("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToDelete);
		list = taskService.list("u5be48d5-ae7c-4816-a210-9c984cf760a0");
		Assert.assertTrue(list.size() == 5);
		for (Task currentTask : list) {
			Assert.assertTrue(!currentTask.getCode().equals(taskCodeToDelete));
		}
	}

	@Test(expected = TaskNotFoundException.class)
	public void deleteTestKoTaskCode() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760aX";
		taskService.delete("u5be48d5-ae7c-4816-a210-9c984cf760a0", taskCodeToDelete);

	}

	@Test(expected = TaskNotFoundException.class)
	public void deleteTestKoUserCode() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
		taskService.delete("u5be48d5-ae7c-4816-a210-9c984cf760aX", taskCodeToDelete);
	}

	@Test(expected = UserNotFoundException.class)
	public void deleteTestNoUSerCode() throws UserNotFoundException, TaskNotFoundException {
		String taskCodeToDelete = "t5be48d5-ae7c-4816-a210-9c984cf760a0";
		taskService.delete("", taskCodeToDelete);
	}
}
