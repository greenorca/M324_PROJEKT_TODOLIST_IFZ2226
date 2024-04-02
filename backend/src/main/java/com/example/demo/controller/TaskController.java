package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@RestController
@RequestMapping("/todo")
public class TaskController {

    @Autowired
	private TaskRepository taskRepository;

	@GetMapping("/")
	public List<Task> getTasks() {

		return taskRepository.findAll();
	}

	@PostMapping("/tasks")
	public ResponseEntity<Task> addTask(@RequestBody String taskdescription) {
		ObjectMapper om = new ObjectMapper();
		Task task;
		try {
			task = om.readValue(taskdescription, Task.class);
			task = taskRepository.save(task);
			return ResponseEntity.ok(task);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(null);
	}

	@PostMapping("/delete")
	public ResponseEntity<String> delTask(@RequestBody String taskdescription) {
		System.out.println("API EP '/delete': '" + taskdescription + "'");
		ObjectMapper om = new ObjectMapper();
		Task task;
		try {
			task = om.readValue(taskdescription, Task.class);
			// 1. suche Task-Datensatz mit gegebenem Inhalt in DB
			Task toDeleteTask = taskRepository.findByTaskdescription(task.getTaskdescription());
			// 2. Wen Task gefunden, lösche den Datensatz in DB
			if (toDeleteTask != null){
				taskRepository.delete(toDeleteTask);
				return ResponseEntity.ok("gelöscht");
			}
		} catch(Exception ex){}
		return ResponseEntity.ok("nicht gefunden");
	}
}
