package com.example.springTest.service;


import com.example.springTest.domain.Role;
import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.UUID;

@Service
public class UsersDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository frmdtb;

    @Value("${hostname}")
    private String hostName;
    @Autowired
    private MailSend mailSend;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Users users=frmdtb.findByUsername(name);
        if(users == null){
            throw new UsernameNotFoundException("User not found exception");
        }
        return users;
    }

    public boolean add_new_user(Users users){
        Users fromDataBase=frmdtb.findByUsername(users.getUsername());
        if(fromDataBase!=null){
            return false;
        }
        users.setActive(false);
        users.setUsername(users.getUsername());
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRoles(Collections.singleton(Role.USER));
        users.setEmail(users.getEmail());
        users.setActivation(UUID.randomUUID().toString());
        sendEmail(users);
        frmdtb.save(users);
        return true;
    }
    public String sendEmail(Users users){
        if(!StringUtils.isEmpty(users.getEmail())) {                // Check if email is empty or null with Spring's isEmpty
            String message = String.format("Hi! %s,\n Welcome to my springTest!\n" +
                    " Please click to link below to activate your account:" +
                    " http://%s/activate/%s", users.getUsername(),
                        hostName,
                        users.getActivation());
            mailSend.send(users.getEmail(), "Activation code", message);
            return "Email sent successfully";
        }
        return "Something went wrong, check the e-mail";
    }
    public boolean isActivated(String code) {
        Users users=frmdtb.findByActivation(code);
        if(users==null){
            return false;
        }
        users.setActivation(null);                                  // To avoid re-activations
        users.setActive(true);
        frmdtb.save(users);
        return true;
    }

}
