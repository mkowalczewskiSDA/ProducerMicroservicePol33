package com.example.ProducerMicroservice.controller;

import com.example.ProducerMicroservice.model.dto.TaskDTO;
import com.example.ProducerMicroservice.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping
    @ApiOperation("Returns all tasks from database")
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@ApiParam(value = "Id of a task which we want to find") @PathVariable int id) {
        return taskService.findById(id);
    }


}
