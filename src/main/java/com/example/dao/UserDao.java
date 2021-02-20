package com.example.dao;

import com.example.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    private Map<Integer, User> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put(1, new User(1, "Jan", "Kowalski"));
        users.put(2, new User(2, "Przykladowy", "User"));
        users.put(3, new User(3, "Mateusz", "Morawiecki"));
        users.put(4, new User(4, "Jaroslaw", "Kaczynski"));
    }

    public User getUserById(int id) {
        return users.get(id);
    }

    public Map<Integer, User> getUsers() {
        return users;
    }
}
