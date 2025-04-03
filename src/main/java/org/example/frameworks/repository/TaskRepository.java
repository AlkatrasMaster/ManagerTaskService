package org.example.frameworks.repository;

import org.example.frameworks.entity.Task;
import org.example.frameworks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
