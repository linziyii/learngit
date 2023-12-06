package com.example.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mysql.mapper")
public class MysqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
    }
}
