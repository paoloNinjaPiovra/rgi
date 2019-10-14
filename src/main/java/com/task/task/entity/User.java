package com.task.task.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "USER_SEQ")
    private Long id;

    @Column(name="CODE", nullable = false, unique = true)
    private String code;

    @Column(name="NAME", nullable = false)
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.code = UUID.randomUUID().toString();
    }

    public User(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return code.equals(user.code) &&
                name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
