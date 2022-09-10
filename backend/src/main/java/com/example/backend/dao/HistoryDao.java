package com.example.backend.dao;

import com.example.backend.entity.*;

import java.util.Date;
import java.util.List;

public interface HistoryDao {
    void addToHistory(String theme, String username, Date time);
    List<History>showHistory(String username);
    void delete(String theme,String username,String time);
}
