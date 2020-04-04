package com.example.springTest.repository;

import com.example.springTest.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Users findByActivation(String code);                           // Name of method is matter indeed
                                                                    // Never listen never use name for variables like 'act_tr' with '_'
    Users findByUsername(String user);
}
