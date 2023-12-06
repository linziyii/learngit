package com.example.mysql.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mysql.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Many;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
