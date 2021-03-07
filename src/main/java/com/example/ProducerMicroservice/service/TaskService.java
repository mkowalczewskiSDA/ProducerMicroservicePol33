package com.example.ProducerMicroservice.service;

import com.example.ProducerMicroservice.model.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    List<TaskDTO> findAll();
    TaskDTO create(TaskDTO taskDTO);
    TaskDTO update(int id, TaskDTO taskDTO);
    void delete(int id);
    TaskDTO findById();
    TaskDTO findFirst();
    TaskDTO findLast();
}
