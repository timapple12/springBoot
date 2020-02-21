package com.example.springTest.domain;
import javax.persistence.*;

@Entity
  
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String message;
    private String tag;

    public Message() {
    }

    public Message(String message, String tag) {
        this.message=message;
        this.tag=tag;
    }

    public Integer getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
    public String getTag() {
        return tag;
    }

    public void setText(String text){
        this.message=text;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
