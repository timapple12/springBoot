package com.example.springTest.controller;

import com.example.springTest.domain.Message;
import com.example.springTest.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class Main {
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
    public String add(@RequestParam String text, @RequestParam String tag, Map<String,Object> model) {
        Message mes = new Message(text, tag);
        try {
            repository.save(mes);
        } catch (Exception e) {
            System.out.println(e);
        }
       Iterable<Message> messages = repository.findAll();


        System.out.println(messages);

        model.put("messages", messages.iterator());
        model.put("filter","");//to avoid error
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        List<Message> messages = repository.findByTag(filter);
        model.put("messages", messages);
        return "main";
    }

}


