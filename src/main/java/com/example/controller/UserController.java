package com.example.controller;

import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "index";
    }
}
