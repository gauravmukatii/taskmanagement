package com.project.taskmanagement.service;

import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task){
        return taskRepo.save(task);
    }






}
