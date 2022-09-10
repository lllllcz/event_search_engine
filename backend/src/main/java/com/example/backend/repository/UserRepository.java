package com.example.backend.repository;
import com.example.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByUsername(String username);
    List<User>findAll();



}
