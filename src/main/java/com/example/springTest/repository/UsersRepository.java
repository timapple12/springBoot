package com.example.springTest.repositorys;

import com.example.springTest.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Users findByUsername(String user);
}
