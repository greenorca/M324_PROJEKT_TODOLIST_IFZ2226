package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

    //@Query("SELECT * FROM Task WHERE taskdecription LIKE %?1%")
    public List<Task> findByTaskdescriptionContaining(String search);

}
