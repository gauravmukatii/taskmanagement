package com.project.taskmanagement.controller;

import com.project.taskmanagement.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepo taskrepo;

    @Autowired
    public TaskController(TaskRepo taskrepo) {
        this.taskrepo = taskrepo;
    }
}
