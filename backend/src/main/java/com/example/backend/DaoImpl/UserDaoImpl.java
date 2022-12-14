package com.example.backend.DaoImpl;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.Utils.SessionUtil;
import com.example.backend.dao.UserDao;
import com.example.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.backend.entity.User;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    private UserRepository userRepository;

    @Override
    public String handleLogin(String username,String password){
        List<User>userList=userRepository.findAll();
        Iterator<User>it=userList.iterator();
        while (it.hasNext()){
            User user=it.next();
            if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
                JSONObject obj = new JSONObject();
                obj.put("userId", user.getId());
                obj.put("username", user.getUsername());
                SessionUtil.setSession(obj);
                System.out.println("login");
                return "success";
            }
        }
        return "failed";
    }

    @Override
    public String handleRegister(String username,String password){
        List<User>userList=userRepository.findAll();
        Iterator<User>it=userList.iterator();
        while (it.hasNext()){
            User user=it.next();
            if(user.getUsername().equals(username)){
                return "用户名已存在！";
            }
        }
        User user=new User(username,password);
        userRepository.save(user);
        return "注册成功！";
    }


}
