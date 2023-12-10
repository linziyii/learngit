package com.example.mysql.controller;

import com.example.mysql.req.UserQueryReq;
import com.example.mysql.req.UserResetReq;
import com.example.mysql.req.UserSaveReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.UserQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping
public class UserControl {
    @Autowired
    private UserService userService;
    @GetMapping("/user/list")
    public CommonResp query(@Valid UserQueryReq userQueryReq){
        CommonResp<PageResp<UserQueryResp>> resp=new CommonResp();
        PageResp<UserQueryResp> list=userService.list(userQueryReq);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/user/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq userSaveReq){
        userSaveReq.setPassword(DigestUtils.md5DigestAsHex(userSaveReq.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp=new CommonResp();
        userService.save(userSaveReq);
        return resp;
    }
    @DeleteMapping("/user/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        userService.delete(id);
        return resp;
    }

    //密码修改功能
    @PostMapping("/user/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetReq userResetReq){
        userResetReq.setPassword(DigestUtils.md5DigestAsHex(userResetReq.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp=new CommonResp();
        userService.resetPassword(userResetReq);
        return resp;
    }
}
