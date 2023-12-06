package com.example.mysql.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mysql.entity.User;
import com.example.mysql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping
public class usercontroller {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/index/{userId}")
    public User index(@PathVariable int userId){
        return userMapper.selectById(userId);
    }

    @GetMapping("/index/next1/{userId}")
    public List<User> getuser(@PathVariable int userId){
        QueryWrapper<User> q=new QueryWrapper<>();
        q.ge("id",1).le("id",2);
        return userMapper.selectList(q);
    }
}

