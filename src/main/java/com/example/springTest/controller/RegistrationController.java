package com.example.springTest.controller;

import com.example.springTest.domain.Users;
import com.example.springTest.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@org.springframework.stereotype.Controller
public class RegistrationController {
    @Autowired(required = false)
    private UsersDetailService usersService;


    @PostMapping("/registration")
    public String addNewUser(Users users, Map<String, Object> model) {
        if (!usersService.add_new_user(users)) {
            model.put("user", "User " + users.getUsername().trim() + " already exist");
            return "registration";
        }
        if(users.getUsername().trim().length()==0||
                users.getPassword().trim().length()==0){
            model.put("user","Invalid values!");
            return "registration";
        }
            return "redirect:/login";
    }
    @GetMapping("/activate/{code}")
    public String activate(Map<String, Object> model, @PathVariable String code){
        boolean iActivated = usersService.isActivated(code);

        if(iActivated){
            model.put("user", "User successfully activated!");
        }else{
            model.put("user","Something went wrong, repeat activation once more please");
        }
        return "login";
    }
}
