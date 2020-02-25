package com.example.springTest.controller;

import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired(required = false)
    private UsersRepository usersRepository;
    @Autowired(required = false)
    Users frmdb;
   /* @GetMapping("/registration")
    public String get(Map<String,Object>model){
        return "registration";
    }*/
    @PostMapping("/registration")
    public String addNewUser(Users users, Map<String,Object> model){
            frmdb= usersRepository.findByUsername(users.getUsername());
        if(frmdb!=null){
        model.put("user","User "+users.getUsername().trim()+" exist");
        return "registration";

        }
        users.setActive(true);
        users.setUsername(users.getUsername());
        users.setPassword(users.getPassword());
        users.setRoles(Collections.singleton(Role.USER));

        usersRepository.save(users);
        return "redirect:/login";
    }
   /* @RequestMapping("/registration")
    public String showUsers(Map<String,Object>model){
        Iterable<Users>listOfUsers=usersRepository.findAll();
        model.put("users",listOfUsers);
        return "registration";
    }*/
}
