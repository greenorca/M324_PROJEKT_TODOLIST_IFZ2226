package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TestTask {

    @Test
    public void testSetGetTaskDescription(){

        String desc = "lalelu";

        Task task = new Task();

        task.setTaskdescription(desc);

        assertEquals(desc, task.getTaskdescription());

    }

    @Test
    public void testSetGetId(){
        int id = 42;
        Task t = new Task();
        t.setId(id);
        assertEquals(id, t.getId());

    }

}
