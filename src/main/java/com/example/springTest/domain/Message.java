package com.example.springTest.domain;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Invalid input, field cannot be null!")
    @Length(max = 4096, message = "Message is too long")
    private String message;
    private String tag;

    //@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private String author;
    private String filename;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Message() {
    }

    public Message(String message, String tag, String author) {
        this.author = author;
        this.message = message;
        this.tag = tag;

    }

   public void setAuthor(String author) {
        this.author = author;
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

    public void setText(String text) {
        this.message = text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAuthorName() {
        return author != null ? author : "has no author";
    }

     public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

}
