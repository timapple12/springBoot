package com.example.springTest;

import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repositorys.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired(required =false)
    private UsersRepository usersRepository;
    public void setUserRepository(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addNewUser(Users users, Map<String,Object> model){
        Users frmdb;
        try {
            frmdb = usersRepository.findUser(users.getPassword());
        }catch (Exception e){
            System.out.println(e);
            return "registration";
        }

        if(frmdb!=null){
            model.put("users","User "+users.getUsername().trim()+" exist");
            return "registration";

        }
        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        usersRepository.save(users);
        return "redirect:/login";
    }
}
