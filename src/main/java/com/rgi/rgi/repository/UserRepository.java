package com.rgi.rgi.repository;

import com.rgi.rgi.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT user FROM User user WHERE user.name = :name")
    User findUserByName(String name);

    @Query(value = "SELECT user FROM User user WHERE user.code = :userCode")
    User findUserByUserCode(String userCode);

    @Query(value = "SELECT user FROM Task task JOIN task.users user WHERE task.code = :taskCode and user.code = :userCode")
    List<User> findUserByTaskCode(String taskCode, String userCode);

}
