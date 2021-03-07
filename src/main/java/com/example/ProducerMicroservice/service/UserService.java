package com.example.ProducerMicroservice.service;

import com.example.ProducerMicroservice.model.User;
import com.example.ProducerMicroservice.model.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findById(int id);
    UserDTO create(UserDTO userDTO);
    UserDTO update(int id, UserDTO userDTO);
    void delete(int id);
    User getUserEntity(int id);
}
