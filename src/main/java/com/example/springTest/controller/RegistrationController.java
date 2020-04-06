package com.example.springTest.controller;

import com.example.springTest.domain.Users;
import com.example.springTest.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@org.springframework.stereotype.Controller
public class RegistrationController {
    @Autowired(required = false)
    private UsersDetailService usersService;


    @PostMapping("/registration")
    public String addNewUser(@Valid Users users,
                             BindingResult bindingResult,
                             Model model) {

        if(bindingResult.hasErrors()){
            Map<String,String> errors=ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return "registration";
        }
        if(!users.getPassword().equals(users.getSecondPassword())||users.getPassword().trim().length()==0){
            model.addAttribute("passwordError","Password incorrect!");
            return "registration";
        }
        if(users.getEmail()==null) {
            model.addAttribute("emailError","Email cannot be null!");
            return "registration";
        }
        if (!usersService.add_new_user(users)||users.getUsername()==null) {
            model.addAttribute("usernameError", "User exist or null");
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
