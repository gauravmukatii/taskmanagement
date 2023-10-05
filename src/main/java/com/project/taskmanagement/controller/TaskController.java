package com.project.taskmanagement.controller;

import com.project.taskmanagement.Entity.Task;
import com.project.taskmanagement.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        try{
            Task createTask = taskService.createTask(task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createTask);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getalltasks")
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/getbyid/{Id}")
    public Task getById(@PathVariable Long Id){
        return taskService.getTaskById(Id);
    }

    @PutMapping("/update/{Id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long Id, @RequestBody Task updatedtask){
        try{
            Task updated = taskService.updateTask(Id, updatedtask);
            return ResponseEntity.status(HttpStatus.CREATED).body(updated);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long Id){
        try{
             taskService.deleteTask(Id);
             return ResponseEntity.ok("Task deleted successfully!");
        }
        catch(EntityNotFoundException e){
             return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete task");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchByTitle(@RequestParam("title") String title){
        List<Task> foundedTitle = taskService.searchByTitle(title);
        return ResponseEntity.ok(foundedTitle);
    }

    @GetMapping("/getoverduetask")
    public ResponseEntity<List<Task>> getOverDueTasks(){
        List<Task> overDueTasks = taskService.getOverDueTasks();
        return ResponseEntity.ok(overDueTasks);
    }

}
