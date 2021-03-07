package com.example.ProducerMicroservice.repository;

import com.example.ProducerMicroservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Task findFirstByIdAsc();

    Task findFirstByIdDesc();

}
