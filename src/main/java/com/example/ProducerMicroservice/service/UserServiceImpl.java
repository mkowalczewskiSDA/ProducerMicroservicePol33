package com.example.ProducerMicroservice.service;

import com.example.ProducerMicroservice.exceptions.UserNotFoundException;
import com.example.ProducerMicroservice.model.User;
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

    @Override
    public UserDTO create(UserDTO userDTO) {
        userRepository.save(modelMapper.map(userDTO, User.class));
        return userDTO;
    }

    @Override
    public UserDTO update(int id, UserDTO userDTO) {
        User user = getUserEntity(id);
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        return modelMapper.map(userRepository.save(user), UserDTO.class); //Jesteśmy konsekwentni w zwracaniu DTO - REST powinien zwrócić nam ciało klasy ktorą dodaliśmy
    }

    private User getUserEntity(int id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
