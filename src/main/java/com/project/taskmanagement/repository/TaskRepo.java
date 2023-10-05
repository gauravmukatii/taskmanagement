package com.project.taskmanagement.repository;

import com.project.taskmanagement.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}
