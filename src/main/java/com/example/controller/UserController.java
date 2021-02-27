package com.example.controller;

import com.example.dao.UserDao;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
//@RequestScope
@SessionScope
public class UserController {

    private int test = 0;

    @PostConstruct
    public void init() {
        System.out.println("Bean has been initialized");
        System.out.println(test);
    }

    @Autowired
    UserDao userDao;

    @RequestMapping("/main/{id}")
    public String mainPageId(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("user", userDao.getUserById(id));
        return "index";
    }

    @RequestMapping("/main_param")
    public String mainPageParamId(@RequestParam("id") int id, Model model){
        model.addAttribute("user", userDao.getUserById(id));
        return "index";
    }

    @RequestMapping("/main")
    public String mainPage(Model model){
        model.addAttribute("users", userDao.getUsers());
        return "index";
    }

    @RequestMapping("/post")
    public String postPage(Model model){
        model.addAttribute("user", new User());
        return "post";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("deleteButton") int id, Model model) {
        userDao.removeUserById(id);
        return "redirect:main";
    }

    @PostMapping("/edit")
    public String editUser(@RequestParam("editButton") int id, Model model) {
        model.addAttribute("user", userDao.getUserById(id));
        test++;
        System.out.println(test);
        return "post";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String postAction(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "post";
        } else {
            System.out.println("Added User: " + user.getFirstName() + " " + user.getLastName());
            userDao.addUser(user);
            return "index";
        }
    }

    @RequestMapping(value = "/edited", method = RequestMethod.POST)
    public String postEdit(User user, Model model){
        System.out.println("Edited User: " + user.getFirstName() + " " + user.getLastName());
        userDao.getUsers().put(user.getId(), user);
        return "redirect:main";
    }
}
