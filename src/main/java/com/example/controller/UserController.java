package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/main")
    public String mainPage(Model model) {
        model.addAttribute("users", userDao.getUsers());
        return "index";
    }

    @RequestMapping("/main/{id}")
    public String mainPageId(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "index";
    }

    @RequestMapping("/main_param")
    public String mainPageParamId(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        return "index";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("deleteButton") int id, Model model) {
        userDao.removeUserById(id);
        return "redirect:main";
    }

    @PostMapping("/save")
    public String saveAction(User user) {
        userDao.addUser(user);
        return "redirect:main";
    }

    @RequestMapping("/post")
    public String postPage(Model model) {
        model.addAttribute("user",new User());
        return "post";
    }
}
