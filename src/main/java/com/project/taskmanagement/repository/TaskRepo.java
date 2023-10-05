package com.project.taskmanagement.repository;

import com.project.taskmanagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByTitle(String title);
    List<Task> findByDueDateBefore(LocalDate dueDate);
}
