package com.example.springTest.controller;

import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import com.example.springTest.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller
public class EditProfileController {
    @Autowired
    private UsersRepository userFromDB;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersDetailService usersDetailService;

    private Users users;

    @GetMapping("/profile")
    public String usr(Principal principal,
                      Map<String,Object> model){
        users=userFromDB.findByUsername(principal.getName());
        //users=user;
        model.put("user","Edit profile of user: "+users.getUsername());
        model.put("email",users.getEmail());
        model.put("username",users.getUsername());
        return "editProfile";
    }
    @PostMapping("/profile")
    public String edit(Users user,
                       Map<String,Object> model){

        if(passwordEncoder.matches(user.getPassword(),users.getPassword())){
            user.setEmail(user.getEmail());
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            System.out.println(user.getPassword());
            if(users.isAdmin()){
                user.setRoles(Collections.singleton(Role.ADMIN));
            }else{
                user.setRoles(Collections.singleton(Role.USER));
            }
            user.setActive(true);
            if(!user.getEmail().equals(users.getEmail())){
                user.setActivation(UUID.randomUUID().toString());
                usersDetailService.sendEmail(user);
                user.setActive(false);
                model.put("message","Changes saved! Check an email to submit");
            }else{
                model.put("message","Changes saved! Sign out first please!");
            }

            userFromDB.delete(users);
            userFromDB.save(user);
            model.put("user","");
        }else{
            model.put("message","Incorrect password!");
        }
        model.put("email",users.getEmail());
        model.put("username",users.getUsername());
        return "editProfile";
    }
}
