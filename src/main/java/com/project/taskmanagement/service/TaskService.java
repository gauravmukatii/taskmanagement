package com.project.taskmanagement.service;

import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.repository.TaskRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private final TaskRepo taskRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task createTask(Task task){

        if(task.getTitle().isEmpty() || task.getTitle() != null){
            throw new IllegalArgumentException("Task title should not be empty or null");
        }

        Task savedTask = taskRepo.save(task);

        LOGGER.info("Task Created with ID : {}", savedTask.getId());

        return savedTask;
    }

//    public Task getAllTasks(){
//
//    }






}
