package com.example.mysql.controller;

import com.example.mysql.req.UserLoginReq;
import com.example.mysql.req.UserQueryReq;
import com.example.mysql.req.UserResetReq;
import com.example.mysql.req.UserSaveReq;
import com.example.mysql.resp.CommonResp;
import com.example.mysql.resp.UserLoginResp;
import com.example.mysql.resp.UserQueryResp;
import com.example.mysql.resp.PageResp;
import com.example.mysql.service.UserService;
import com.example.mysql.util.SnowFlake;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.TimeoutUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping
public class UserControl {
    @Autowired
    private UserService userService;
    @Resource
    private RedisTemplate redisTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private SnowFlake snowFlake;
    @ApiOperation(value = "")
    @GetMapping("/user/list")
    public CommonResp query(@Valid UserQueryReq userQueryReq){
        CommonResp<PageResp<UserQueryResp>> resp=new CommonResp();
        PageResp<UserQueryResp> list=userService.list(userQueryReq);
        resp.setContent(list);
        return resp;
    }
    @ApiOperation(value = "保存用户信息",notes = "传入昵称、密码")
    @PostMapping("/user/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq userSaveReq){
        userSaveReq.setPassword(DigestUtils.md5DigestAsHex(userSaveReq.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp=new CommonResp();
        userService.save(userSaveReq);
        return resp;
    }
    @ApiOperation(value = "删除用户",notes = "传入用户id")
    @DeleteMapping("/user/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp=new CommonResp();
        userService.delete(id);
        return resp;
    }

    //密码修改功能
    @ApiOperation(value = "重置用户密码",notes = "参数要求：修改后的密码")
    @PostMapping("/user/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetReq userResetReq){
        userResetReq.setPassword(DigestUtils.md5DigestAsHex(userResetReq.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp=new CommonResp();
        userService.resetPassword(userResetReq);
        return resp;
    }
    @PostMapping("/user/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq userLoginReq){
        userLoginReq.setPassword(DigestUtils.md5DigestAsHex(userLoginReq.getPassword().getBytes(StandardCharsets.UTF_8)));
        CommonResp resp=new CommonResp();
        UserLoginResp userLoginResp=userService.login(userLoginReq);
        Long token= snowFlake.nextId();
        LOG.info("生成token：{}，并放入redis中",token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(),userLoginResp,3600*24, TimeUnit.SECONDS);
        resp.setContent(userLoginResp);
        return resp;
    }
    @GetMapping("/user/logout/{token}")
    public CommonResp logout(@PathVariable String token){
        CommonResp commonResp=new CommonResp();
        redisTemplate.delete(token);
        LOG.info("从redis中删除token：{}",token);
        commonResp.setMessage("token已经清除，退出登录成功！");
        return commonResp;
    }

}
