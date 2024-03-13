package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Task;

@DataJpaTest
public class TaskRepositoryTest {
    
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testFindTaskByDescription(){

        /*
         * 1. check Table does not contain specific task (yet)
         * 2. create and store task
         * 3. find task
         * 
         */
        final String test_me = "test me";
        Task firstResult = taskRepository.findByTaskdescription(test_me);

        assertTrue(null == firstResult, "no task should have the description yet");
        
        Task t = new Task();
        t.setTaskdescription(test_me);
        taskRepository.save(t);

        Task findMe = taskRepository.findByTaskdescription(test_me);
        assertNotNull(findMe, "Task should have been found by now");
        assertEquals(test_me, findMe.getTaskdescription());
    }

}
