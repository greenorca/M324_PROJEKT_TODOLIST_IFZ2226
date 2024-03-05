package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Task;
import com.example.demo.repositories.TaskRepository;

@RestController
public class TaskController {
@Autowired
	private TaskRepository taskRepository;

	@CrossOrigin
	@GetMapping("/")
	public List<Task> getTasks() {
		
		return taskRepository.findAll(); // actual task list (internally converted to a JSON stream)
	}

	@CrossOrigin
	@PostMapping("/tasks")
	public String addTask(@RequestBody String taskdescription) {
		
		Task t = new Task();
		t.setTaskdescription(taskdescription);
		t = taskRepository.save(t);
		System.out.println("Task saved, ID: "+t.getId());

		return "redirect:/";
	}

	@CrossOrigin
	@GetMapping("/find")
	public List<Task> findTasks(@RequestParam String search){
		return taskRepository.findByTaskdescriptionContaining(search);
	}

	@CrossOrigin
	@PostMapping("/delete")
	public String delTask(@RequestBody @NonNull Task task) {
		
		taskRepository.delete(task);

		return "redirect:/";
	}
}
