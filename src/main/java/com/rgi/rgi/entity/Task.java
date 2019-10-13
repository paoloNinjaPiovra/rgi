package com.rgi.rgi.entity;

import com.rgi.rgi.enums.Status;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator = "TASK_SEQ")
    @SequenceGenerator(sequenceName = "user_seq", allocationSize = 1, name = "TASK_SEQ")
    private Long id;

    @Column(name="CODE", nullable = false, unique = true)
    private String code;

    @Column(name="NAME")
    private String name;

    @Column(name="DESCRIPTION")
    private String description;

    @Column(name="STATUS", nullable = false)
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TASK_USER",
            joinColumns = { @JoinColumn(name = "TASK_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) }
    )
    private Set<User> users = new HashSet<>();

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.status = Status.NEW;
        this.code = UUID.randomUUID().toString();
    }

    public Task(String name, String description, String code) {
        this.name = name;
        this.description = description;
        this.status = Status.NEW;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}