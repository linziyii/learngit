package com.example.mysql.service;

import com.example.mysql.entity.User;
import com.example.mysql.entity.UserExample;
import com.example.mysql.exception.BusinessException;
import com.example.mysql.exception.BusinessExceptionCode;
import com.example.mysql.mapper.UserMapper;
import com.example.mysql.req.UserLoginReq;
import com.example.mysql.req.UserQueryReq;
import com.example.mysql.req.UserResetReq;
import com.example.mysql.req.UserSaveReq;
import com.example.mysql.resp.UserLoginResp;
import com.example.mysql.resp.UserQueryResp;
import com.example.mysql.resp.PageResp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
    @Resource
    private UserMapper userMapper;
    public PageResp<UserQueryResp> list(UserQueryReq userQueryReq){
        UserExample example=new UserExample();
        UserExample.Criteria criteria=example.createCriteria();
        if(!ObjectUtils.isEmpty(userQueryReq.getLoginName())){
            criteria.andNameLike("%"+ userQueryReq.getLoginName()+"%");  //作用：定义select规则，将其传入mapper的select可起到作用
        }
        PageHelper.startPage(userQueryReq.getPage(), userQueryReq.getSize());
        List<User> userList=userMapper.selectByExample(example);
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        List<UserQueryResp> userQueryResp =new ArrayList<>();
        for(User user:userList){
            UserQueryResp e=new UserQueryResp();
            BeanUtils.copyProperties(user,e);  //复制对象
            userQueryResp.add(e);   //添加到列表中
        }
        PageResp<UserQueryResp> pageResp=new PageResp<>();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(userQueryResp);
        return pageResp;
    }
    public void save(UserSaveReq userSaveReq){
        User user=new User();
        BeanUtils.copyProperties(userSaveReq,user);
        if(ObjectUtils.isEmpty(userSaveReq.getId())){
            if(ObjectUtils.isEmpty(CheckLoginName(userSaveReq.getLoginName()))){
                userMapper.insert(user);
            }else {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }else{
            UserExample userExample=new UserExample();
            userExample.createCriteria().andIdEqualTo(userSaveReq.getId());
            userMapper.updateByExample(user,userExample);
        }
    }
    public void delete(Long loginname){
    }
    public User CheckLoginName(String Loginname){
        UserExample example=new UserExample();
        example.createCriteria().andLoginNameEqualTo(Loginname);
        List<User> userList=userMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        } else{
            return userList.get(0);
        }
    }

    public void resetPassword(UserResetReq userResetReq){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andIdEqualTo(userResetReq.getId());
        User user=new User();
        BeanUtils.copyProperties(userResetReq,user);
        userMapper.updateByExampleSelective(user,userExample);
    }
    public UserLoginResp login(UserLoginReq userLoginReq){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(userLoginReq.getLoginName());
        List<User> userList=userMapper.selectByExample(userExample);
        if(ObjectUtils.isEmpty(userList)){
            LOG.info("用户名不存在，{}",userLoginReq.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }else{
            if(userList.get(0).getPassword().equals(userLoginReq.getPassword())){
                UserLoginResp userLoginResp=new UserLoginResp();
                BeanUtils.copyProperties(userList.get(0),userLoginResp);
                return userLoginResp;
            }else{
                LOG.info("密码不对，输入密码：{}，数据库密码：{}",userLoginReq.getPassword(),userList.get(0).getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }
}
