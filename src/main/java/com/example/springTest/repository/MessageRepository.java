package com.example.springTest.repository;


import com.example.springTest.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
     List<Message> findByTag(String tag);
}
