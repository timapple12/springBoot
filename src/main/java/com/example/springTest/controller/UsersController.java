package com.example.springTest.controller;

import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UsersController{
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public String usr(Map<String,Object>model){
        model.put("user",usersRepository.findAll());
        return "user";
    }
    @GetMapping("{user}")
    public String editUserRole(@PathVariable Users user, Map<String,Object>model){
        model.put("user",user);
        model.put("roles", Role.values());
        return "saveUser";
    }
    @PostMapping
    public String saveUserRole(@RequestParam("user_id")Users user,
                               @RequestParam String username,
                               @RequestParam Map<String,String>model){
        user.setUsername(username);
        Set<String> roles = Arrays
                .stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : model.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        usersRepository.save(user);

        return "redirect:/user";
    }

}
