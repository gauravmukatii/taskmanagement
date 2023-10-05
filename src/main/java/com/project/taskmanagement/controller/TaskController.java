package com.project.taskmanagement.controller;

import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public ResponseEntity<Task> createTask(@RequestBody Task task){
        try{
            Task createTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createTask);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
