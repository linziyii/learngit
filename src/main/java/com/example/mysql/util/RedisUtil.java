package com.example.mysql.util;

import com.mysql.jdbc.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    private static final Logger LOG= LoggerFactory.getLogger(RedisUtil.class);
    @Resource
    private RedisTemplate redisTemplate;
    public boolean validateRepeat(String key,long second){
        if(redisTemplate.hasKey(key)){
            LOG.info("key已存在");
            return false;
        }else{
            LOG.info("key不存在");
            redisTemplate.opsForValue().set(key,key,second, TimeUnit.SECONDS);
            return true;
        }
    }
}
