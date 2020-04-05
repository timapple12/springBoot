package com.example.springTest.service;


import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service

public class UsersDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository frmdtb;

    @Autowired
    private MailSend mailSend;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return frmdtb.findByUsername(name);
    }

    public boolean add_new_user(Users users){
        Users fromDataBase=frmdtb.findByUsername(users.getUsername());
        if(fromDataBase!=null){
            return false;
        }
        users.setActive(false);
        users.setUsername(users.getUsername());
        users.setPassword(users.getPassword());
        users.setRoles(Collections.singleton(Role.USER));
        users.setEmail(users.getEmail());
        users.setActivation(UUID.randomUUID().toString());
        if(!StringUtils.isEmpty(users.getEmail())){              // Check if email is empty or null
            String message = String.format("Hi! %s,\n Welcome to my springTest!\n" +
                    " Please click to link below to activate your account:" +
                    " http://localhost:8181/activate/%s",users.getUsername(),users.getActivation());
            mailSend.send(users.getEmail(),"Activation code", message);
        }
        frmdtb.save(users);
        return true;
    }

    public boolean isActivated(String code) {
        Users users=frmdtb.findByActivation(code);
        if(users==null){
            return false;
        }
        users.setActivation(null);
        users.setActive(true);// To avoid re-activations
        frmdtb.save(users);
        return true;
    }
}
