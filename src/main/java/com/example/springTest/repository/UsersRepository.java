package com.example.springTest.repository;

import com.example.springTest.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users,Long> {
   Users findByUsername(String user);
}
