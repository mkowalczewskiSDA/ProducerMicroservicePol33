package com.example.controller;

import com.example.dao.OrderDao;
import com.example.dao.UserDao;
import com.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;

    @RequestMapping("/list")
    public String mainPage(Model model) {
        model.addAttribute("orders", orderDao.getOrders());
        return "orders";
    }

    @RequestMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("users", userDao.getUsers());
        return "post_orders";
    }

    @PostMapping("/save")
    public String saveOrder(Order order, @RequestParam("id") int id) {
        order.setUser(userDao.getUserById(id));
        orderDao.addOrder(order);
        return "redirect:list";
    }

}
