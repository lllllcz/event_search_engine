package com.example.backend.DaoImpl;
import com.example.backend.dao.*;
import com.example.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.backend.entity.*;

import java.util.Date;
import java.util.List;
@Repository
public class HistoryDaoImpl implements HistoryDao{
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void addToHistory(String theme, String username, Date time){
        History history=new History(time,theme,username);
        historyRepository.save(history);
    }

    @Override
    public List<History> showHistory(String username){
        return historyRepository.findHistoryByUsername(username);
    }

    @Override
    public void delete(String theme, String username, String time){
       List<History>histories=historyRepository.findHistoryByUsernameAndTheme(username,theme);
       int len=histories.size();
       // System.out.println(time);
        for (int i = 0; i <len ; i++) {
            if(histories.get(i).getTime().toString().equals(time)){
                System.out.println(time);
                History history=histories.get(i);
                historyRepository.delete(history);
                return;
            }
        }

    }

}
