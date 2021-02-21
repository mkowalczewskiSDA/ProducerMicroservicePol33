package com.example.controller;

import com.example.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderDao orderDao;

    @RequestMapping("/list")
    public String mainPage(Model model) {
        model.addAttribute("orders", orderDao.getOrders());
        return "orders";
    }
}
