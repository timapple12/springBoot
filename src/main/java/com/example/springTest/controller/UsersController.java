package com.example.springTest.controller;

import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN')")
public class UsersController {
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public String usr(Map<String,Object>model){
        model.put("user",usersRepository.findAll());
        return "user";
    }
    @GetMapping("{user}")
    public String saveUserRoll(@PathVariable Users user, Map<String,Object>model){
        model.put("user",user);
        model.put("rolles", Role.values());
        return "saveUser";
    }
}
