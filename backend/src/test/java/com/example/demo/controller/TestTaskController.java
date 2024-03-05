package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.contains;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.SubstringMatcher;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
class TestTaskController {

	@Autowired TaskRepository taskRepository;
	@Autowired TaskController taskController;
	@Autowired MockMvc mockMvc;

	@BeforeEach
	public void setup(){
		mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
	}

	@Test 
	void setupOk(){
		assertNotNull(taskController);
		assertNotNull(mockMvc);
	}

    @Test
    @Transactional 
    public void testEmptyTaskListViaREST(){
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
            .andExpect(MockMvcResultMatchers.content().string(
                Matchers.containsString("[]")));
        } catch (Exception e) {
            fail("test fucked up because "+e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    public void testAddTaskListViaREST(){
        final String task = "test task";
        JSONObject t = new JSONObject();
        try {
            t.put("taskdescription", task);
        } catch (JSONException e) {
            fail("crashed on creating JSON param");
        }
        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
            .content(t.toString())
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("utf-8"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print());

            mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
            .andExpect(MockMvcResultMatchers.content().string(
                    Matchers.containsString(task)
                ));
        } catch (Exception e) {
            fail("test fucked up because "+e.getMessage());
            e.printStackTrace();
        }
    }

}