package com.rgi.rgi;

import com.rgi.rgi.repository.TaskRepository;
import com.rgi.rgi.repository.UserRepository;
import com.rgi.rgi.service.TaskService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskPostTests extends Application {

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

}
