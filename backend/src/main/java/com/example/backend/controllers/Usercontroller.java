package com.example.backend.controllers;
import com.alibaba.fastjson.JSONObject;
import com.example.backend.Utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;
import com.example.backend.service.UserService;

@CrossOrigin
@RestController
public class Usercontroller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login/{username}/{password}")
    @ResponseBody
    public String handleLogin(@PathVariable String username, @PathVariable String password){
         return JSON.toJSONString(userService.handleLogin(username,password));
    }


    @RequestMapping(value = "/register/{username}/{password}")
    @ResponseBody
    public String handleRegister(@PathVariable String username, @PathVariable String password){
        return JSON.toJSONString(userService.handleRegister(username,password));
    }

    @RequestMapping("/checkSession")
    public JSONObject checkSession(){
        return userService.checkSession();
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
        SessionUtil.removeSession();
        System.out.println("logout");
        return "logout";
    }




}
