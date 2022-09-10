package com.example.backend.repository;
import com.example.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Integer>{
    Theme findThemeByTheme(String theme);
}
