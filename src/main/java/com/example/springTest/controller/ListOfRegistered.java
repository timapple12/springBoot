package com.example.springTest.controller;

import com.example.springTest.domain.Users;
import com.example.springTest.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.Map;

/*language=SQL*/
//class for showing all registered users when you are on registration page
@Controller
public class ListOfRegistered {

    private final String url="jdbc:mysql://localhost:3306/db?useSSL=false";
    private final String usr="root";
    private final String password="root";
    private final String request="select u.username from users u";
    public static Connection connection;
    public String string = null;
    @Autowired
    private UsersRepository usersRepository;

    public ListOfRegistered(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    protected void connectToDb(){
        try {
            connection= DriverManager.getConnection(url,usr,password);
        } catch (SQLException e) {
           System.out.println(e);
        }
    }

    @GetMapping("/registration")
    public
    String showAllRegUsers(Map<String,Object> model){
        connectToDb();
       Iterable<Users>temp=usersRepository.findAll();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(request);
            if (usersRepository.findAll()!=null){
                model.put("users",temp);
            }else{
                model.put("users","there a'int registered users yet");
            }

        }catch (Exception e){
            System.out.println(e);
        }
        return "registration";
    }
}
