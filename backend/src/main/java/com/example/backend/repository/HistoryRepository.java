package com.example.backend.repository;
import com.example.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
public interface HistoryRepository extends JpaRepository<History,Integer> {

    List<History>findHistoryByUsername(String username);

    @Query(value="select * from history a where a.theme=:theme and a.username=:username ",nativeQuery = true)
    List<History> findHistoryByUsernameAndTheme(String username, String theme);
}
