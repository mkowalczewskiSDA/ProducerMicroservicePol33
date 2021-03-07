package com.example.ProducerMicroservice.controller;

import com.example.ProducerMicroservice.model.dto.UserDTO;
import com.example.ProducerMicroservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {return userService.findAll();}

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDTO create(@Valid @RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }

}
