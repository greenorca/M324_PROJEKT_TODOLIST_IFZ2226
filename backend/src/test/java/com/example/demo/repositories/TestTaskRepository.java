package com.example.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.example.demo.model.Task;

@DataJpaTest
public class TestTaskRepository {

        @Autowired
        private TestEntityManager entityManager;

        @Autowired
        private TaskRepository taskRepository;

        @Test
        public void TestSetup(){
            assertNotNull(entityManager);
            assertNotNull(taskRepository);
        }

        @Test
        public void testAddFind(){

            assertTrue(taskRepository.findByTaskdescriptionContaining("jing").size()==0,"initial stock must be empty");
            final String task = "test";
            Task t = new Task();
            t.setTaskdescription(task);
            t = taskRepository.save(t);
            assertNotNull(t.getId(),"ID must be auto generated");
            assertTrue(t.getId()>0, "ID must be greater 0");
            List<Task> result = taskRepository.findByTaskdescriptionContaining("es"); 
            assertTrue(result.size()==1,"Result must contain one Task");
            assertEquals(result.get(0).getTaskdescription(), task, "task descriptions must match");
        }
}
