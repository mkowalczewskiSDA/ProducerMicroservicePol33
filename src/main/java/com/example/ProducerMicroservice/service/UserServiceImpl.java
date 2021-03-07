package com.example.ProducerMicroservice.service;

import com.example.ProducerMicroservice.exceptions.UserNotFoundException;
import com.example.ProducerMicroservice.model.dto.UserDTO;
import com.example.ProducerMicroservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(int id) {
        return modelMapper.
                map(userRepository.findById(id)
                        .orElseThrow(UserNotFoundException::new), UserDTO.class);


    }
}
