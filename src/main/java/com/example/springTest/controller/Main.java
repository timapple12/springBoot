package com.example.springTest.controller;

import com.example.springTest.domain.Message;
import com.example.springTest.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class Main {
    @Value("${upload.path}")    //   load the directory name from application.properties
    private String uploadPath;
    @Autowired
    private MessageRepository repository;

    @GetMapping("/")
    public String g(Map<String, Object> model) {
        return "greeting";
    }

    @PostMapping("/")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "Oleksandr") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = repository.findAll();
        if (repository.findAll() != null) {
            model.put("messages", messages);
        } else {
            model.put("messages", "there're no data");
        }

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal String user,                 // deprecated, in future replace with Users(class) instead String
                      @Valid Message message,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile multipartFile
                      ) throws IOException {                         // There must be Model, and this must be after bindingResult
        if(bindingResult.hasErrors())
        {                                                                                                           // error handling
            Map<String,String> errorMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("message",message);
        }else
            {
            System.out.println(authToken().getUsername());
            user=authToken().getUsername();
            message.setAuthor(user);
            if (multipartFile != null) {
                File fileDirectory = new File(uploadPath);
                if (!fileDirectory.exists()) {
                    fileDirectory.mkdir();
                }
                String fileUUID = UUID.randomUUID().toString();
                String res = fileUUID + "." + multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(uploadPath + "/" + res));
                message.setFilename(res);
            }
            try {
                model.addAttribute("message",null);
                repository.save(message);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        Iterable<Message> messages = repository.findAll();
        System.out.println(messages);

        model.addAttribute("messages", messages.iterator());
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Message> messages = repository.findByTag(filter);
        model.put("messages", messages);
        return "redirect:/main";
    }
    public UserDetails authToken(){
        AbstractAuthenticationToken auth = (AbstractAuthenticationToken)    // this gets all information from SpringSecurity
                SecurityContextHolder.getContext().getAuthentication();
        UserDetails details = (UserDetails) auth.getPrincipal();// UserDetails - integrated class into SpringSecurity
        return  details;
    }

}


