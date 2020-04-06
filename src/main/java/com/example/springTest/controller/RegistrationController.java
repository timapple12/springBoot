package com.example.springTest.controller;

import com.example.springTest.domain.Users;
import com.example.springTest.domain.dto.CaptchaResponseDTO;
import com.example.springTest.service.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@org.springframework.stereotype.Controller
public class RegistrationController {
    private final String URL_CAPTCHA= "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired(required = false)
    private UsersDetailService usersService;

    @Value("${recaptcha.secret}")
    private String captchaSecret;

    @PostMapping("/registration")
    public String addNewUser(@RequestParam("g-recaptcha-response")String captchaResponse,       // Response from user
                             @Valid Users users,
                             BindingResult bindingResult,
                             Model model) {
        String url = String.format(URL_CAPTCHA,captchaSecret,captchaResponse);
        CaptchaResponseDTO responseDTO = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDTO.class);
        if(!responseDTO.isSuccess()){
            model.addAttribute("captchaError","Something went wrong, recaptcha got an error");
        }

        if(!users.getPassword().equals(users.getSecondPassword())||users.getPassword().trim().length()==0){
            model.addAttribute("passwordError","Password incorrect!");
            return "registration";
        }
        if(users.getEmail()==null) {
            model.addAttribute("emailError","Email cannot be null!");
            return "registration";
        }
        if(bindingResult.hasErrors()||!responseDTO.isSuccess()){
            Map<String,String> errors=ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
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
