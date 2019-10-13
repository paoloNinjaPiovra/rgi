package com.rgi.rgi;

import com.rgi.rgi.entity.Task;
import com.rgi.rgi.entity.User;
import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
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
public class TaskPatchTests {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	UserRepository userRepository;

	@Test
	public void patchTest() throws Exception {

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
}
