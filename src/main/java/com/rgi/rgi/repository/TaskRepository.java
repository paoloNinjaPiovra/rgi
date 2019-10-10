package com.rgi.rgi.repository;

import com.rgi.rgi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "SELECT task FROM Task task JOIN task.users user WHERE task.code = :taskCode and user.code = :userCode")
    Task findTaskByCode(String taskCode, String userCode);

    @Query(value = "SELECT task FROM Task task JOIN task.users user WHERE user.code = :userCode")
    List<Task> findTask(String userCode);
}
