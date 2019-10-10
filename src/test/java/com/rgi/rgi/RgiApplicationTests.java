package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.enums.Status;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
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

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RgiApplicationTests {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Test
	public void getTaskListTest() throws Exception {

		// Given
		HttpUriRequest request = new HttpGet( "https://localhost/task");
		request.addHeader("user-session", "u5be48d5-ae7c-4816-a210-9c984cf760a0");
		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		assertThat(
				httpResponse.getStatusLine().getStatusCode(),
				equalTo(HttpStatus.SC_NOT_FOUND));
	}

	/*@Test
	public void
	givenUserExists_whenUserInformationIsRetrieved_thenRetrievedResourceIsCorrect()
			throws Exception {

		// Given
		HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );

		// When
		HttpResponse response = HttpClientBuilder.create().build().execute( request );

		// Then
		*//*GitHubUser resource = RetrieveUtil.retrieveResourceFromResponse(
				response, GitHubUser.class);*//*
		//assertThat( "eugenp", Matchers.is( resource.getLogin() ) );
	}*/

	@Before
	public void setUp() {
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

		Set<User> userSet = new HashSet<>();
		userSet.add(user);
		Set<User> userSet1 = new HashSet<>();
		userSet1.add(user);
		userSet1.add(user1);
		Set<User> userSet2 = new HashSet<>();
		userSet2.add(user);
		userSet2.add(user1);
		userSet2.add(user2);
		Set<User> userSet3 = new HashSet<>();
		userSet3.add(user);
		userSet3.add(user1);
		userSet3.add(user2);
		userSet3.add(user3);
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

		Task task = new Task("task 0", "task 0 description");
		task.setCode("t5be48d5-ae7c-4816-a210-9c984cf760a0");
		task.setUsers(userSet);
		taskRepository.save(task);

		Task task1 = new Task("task 1 ", "task 1 description");
		task1.setCode("t5be48d5-ae7c-4816-a210-9c984cf760a1");
		task1.setUsers(userSet1);
		taskRepository.save(task1);

		Task task2 = new Task("task 2", "task 2 description");
		task2.setCode("t5be48d5-ae7c-4816-a210-9c984cf760a2");
		task2.setUsers(userSet2);
		taskRepository.save(task2);

		Task task3 = new Task("task 3", "task 3 description");
		task3.setCode("t5be48d5-ae7c-4816-a210-9c984cf760a3");
		task3.setUsers(userSet3);
		taskRepository.save(task3);

		Task task4 = new Task("task 4", "task 4 description");
		task4.setCode("t5be48d5-ae7c-4816-a210-9c984cf760a4");
		task4.setUsers(userSet4);
		taskRepository.save(task4);

		Task task5 = new Task("task 5", "task 5 description");
		task5.setCode("t5be48d5-ae7c-4816-a210-9c984cf760a5");
		task5.setUsers(userSet5);
		taskRepository.save(task5);
	}
}
