package com.example.dao;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDao {

    @Autowired
    UserDao userDao;

    private Map<Integer, Order> orders = new HashMap<>();

    @PostConstruct
    public void init(){
        Map<Integer, User> users = userDao.getUsers();
        orders.put(1, new Order(1, 10, "Test1", users.get(4)));
        orders.put(2, new Order(2, 50, "Test2", users.get(3)));
        orders.put(3, new Order(3, 34, "Test3", users.get(1)));
        orders.put(4, new Order(4, 4, "Test4", users.get(2)));
    }

    public Map<Integer, Order> getOrders() {
        return orders;
    }
}
