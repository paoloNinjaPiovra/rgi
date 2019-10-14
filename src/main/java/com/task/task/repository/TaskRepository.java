package com.task.task.repository;

import com.task.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT task FROM Task task JOIN task.users user WHERE task.code = :taskCode and user.code = :userCode")
    Task findTaskByCodeAndUserCode(String taskCode, String userCode);

    @Query(value = "SELECT task FROM Task task JOIN task.users user WHERE task.code = :taskCode")
    Task findTaskByCode(String taskCode);

    @Query(value = "SELECT task FROM Task task JOIN task.users user WHERE user.code = :userCode")
    List<Task> findTask(String userCode);
}
