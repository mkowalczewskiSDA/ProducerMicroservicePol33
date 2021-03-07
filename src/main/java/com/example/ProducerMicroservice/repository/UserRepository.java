package com.example.ProducerMicroservice.repository;

import com.example.ProducerMicroservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
