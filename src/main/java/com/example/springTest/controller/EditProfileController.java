package com.example.springTest.controller;

import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@Controller
public class EditProfileController {
    @Autowired
    private UsersRepository userFromDB;
    private Users users;

    @GetMapping("/profile")
    public String usr(Principal principal,
                      Map<String,Object> model){
        users=userFromDB.findByUsername(principal.getName());
        //users=user;
        model.put("user","Edit profile of user: "+users.getUsername());
        model.put("email",users.getEmail());
        model.put("username",users.getUsername());
        model.put("password",users.getPassword());

        return "editProfile";
    }
    @PostMapping("/profile")
    public String edit(Users user,
                       Map<String,Object> model){
        userFromDB.delete(users);
        System.out.println(user);
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setUsername(user.getUsername());
        if(users.isAdmin()){
            user.setRoles(Collections.singleton(Role.ADMIN));
        }else{
            user.setRoles(Collections.singleton(Role.USER));
        }
        user.setActive(true);
        userFromDB.save(user);
        model.put("user","");
        model.put("message","Changes saved! Sign out first please!");
        return "editProfile";
    }

}
