package com.backend.ToDoList.repository;

import com.backend.ToDoList.entity.Task;
import com.backend.ToDoList.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user, Pageable pageable);
}
