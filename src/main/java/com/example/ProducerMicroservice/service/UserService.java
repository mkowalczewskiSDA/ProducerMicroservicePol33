package com.example.ProducerMicroservice.service;

import com.example.ProducerMicroservice.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
}
