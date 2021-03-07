package com.example.ProducerMicroservice.service;

import com.example.ProducerMicroservice.exceptions.TaskNotFoundException;
import com.example.ProducerMicroservice.exceptions.UserNotFoundException;
import com.example.ProducerMicroservice.model.Task;
import com.example.ProducerMicroservice.model.User;
import com.example.ProducerMicroservice.model.dto.TaskDTO;
import com.example.ProducerMicroservice.repository.TaskRepository;
import com.example.ProducerMicroservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TaskDTO> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(task -> modelMapper
                        .map(task, TaskDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        taskRepository.save(modelMapper.map(taskDTO, Task.class));
        return taskDTO;
    }

    @Override
    public TaskDTO update(int id, TaskDTO taskDTO) {
        Task task = getTaskEntity(id);
        if (task.getDescription() != null) {
            task.setDescription(taskDTO.getDescription());
        }
        task.setCompleted(taskDTO.isCompleted()); //to pole może być null
        task.setCompletionPercentage(taskDTO.getCompletionPercentage());//jw
        if (taskDTO.getUser() != null) { //user może być null, ale gdy nie jest null, powinien być encją pochodzącą z bazy danych
            task.setUser(userService.getUserEntity(taskDTO.getUser().getId()));
        } else {
            task.setUser(null);
        }
        return modelMapper.map(taskRepository.save(task), TaskDTO.class);
    }

    @Override
    public void delete(int id) {
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO findById(int id) {
        return modelMapper.map(taskRepository.findById(id).orElseThrow(TaskNotFoundException::new), TaskDTO.class);
    }

    @Override
    public TaskDTO findFirst() {
        return modelMapper.map(taskRepository.findFirstByIdOrderByIdAsc(), TaskDTO.class);
    }

    @Override
    public TaskDTO findLast() {
        return modelMapper.map(taskRepository.findFirstByIdOrderByIdDesc(), TaskDTO.class);
    }

    private Task getTaskEntity(int id) {
        return taskRepository.findById(id).orElseThrow(TaskNotFoundException::new);
    }
}
