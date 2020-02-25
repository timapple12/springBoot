package com.example.springTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.Map;
//class for showing all registered users when you are on registration page
@Controller
public class ListOfRegistered {
    private final String url="jdbc:mysql://localhost:3306/db?useSSL=false";
    private final String usr="root";
    private final String password="vasyapidr"; //lol, just a joke
    private final String request="select u.username from users u";
    public static Connection connection;
    protected void connectToDb(){
        try {
            connection= DriverManager.getConnection(url,usr,password);
        } catch (SQLException e) {
           System.out.println(e);
        }
    }

    @GetMapping("/registration")
    public
    String showAllRegUsers(Map<String,Object>model){
        String str="";
        connectToDb();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(request);
            while(resultSet.next()){
                str+=resultSet.getString(1)+"\t";
            }
            model.put("user2",str);
        }catch (Exception e){
            System.out.println(e);
        }
        return "registration";
    }
}
