package com.example.ProducerMicroservice.controller;

import com.example.ProducerMicroservice.model.dto.TaskDTO;
import com.example.ProducerMicroservice.repository.TaskRepository;
import com.example.ProducerMicroservice.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @ApiOperation("Returns all tasks from database")
    public List<TaskDTO> findAll() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@ApiParam(value = "Id of a task which we want to find") @PathVariable int id) {
        return taskService.findById(id);
    }

    @PostMapping
    public TaskDTO create(@Valid @RequestBody TaskDTO taskDTO) {
        return taskService.create(taskDTO);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable int id, @RequestBody TaskDTO taskDTO) {
        return taskService.update(id, taskDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        taskService.delete(id);
    }

    @GetMapping("/first")
    public TaskDTO getFirstTask() {
        return taskService.findFirst();
    }

    @GetMapping("/last")
    public TaskDTO getLastTask() {
        return taskService.findLast();
    }


}
