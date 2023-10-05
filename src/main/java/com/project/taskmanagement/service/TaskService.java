package com.project.taskmanagement.service;

import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.repository.TaskRepo;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskService {

    @Autowired
    private final TaskRepo taskRepo;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    @Async
    public Task createTask(Task task){

        if(task.getTitle() == null || task.getTitle().isEmpty()){
            throw new IllegalArgumentException("Task title should not be empty or null");
        }

        Task savedTask = taskRepo.save(task);

        LOGGER.info("Task Created with ID : {}", savedTask.getId());

        return savedTask;
    }

    @Async
    public List<Task> getAllTasks(){
//        List<Task> tasks = new ArrayList<Task>();
//        taskRepo.findAll().forEach(task -> tasks.add(task));
//        return tasks;
        List<Task> tasks = taskRepo.findAll();
        return tasks;
    }

    public Task getTaskById(Long Id){
        return taskRepo.findById(Id).orElse(null);
    }

    public Task updateTask(Long Id, Task updatedTask){
        Task existingTask = taskRepo.findById(Id).orElseThrow(() -> new EntityNotFoundException("Task not exist"));

        if (updatedTask.getTitle() != null) {
            existingTask.setTitle(updatedTask.getTitle());
        }
        if (updatedTask.getDescription() != null) {
            existingTask.setDescription(updatedTask.getDescription());
        }
        if (updatedTask.getDueDate() != null) {
            existingTask.setDueDate(updatedTask.getDueDate());
        }

        Task updated = taskRepo.save(existingTask);

        return updated;
    }

    public void deleteTask(Long Id){
        Task task = taskRepo.findById(Id).orElseThrow(() -> new EntityNotFoundException("Task not existed so check its id once again"));

        taskRepo.deleteById(Id);

    }

    public List<Task> searchByTitle(String title){
          List<Task> taskFound = taskRepo.findByTitle(title);
          return taskFound;
    }

    public List<Task> getOverDueTasks(){
        LocalDate currentDate = LocalDate.now();
        return taskRepo.findByDueDateBefore(currentDate);
    }

}
