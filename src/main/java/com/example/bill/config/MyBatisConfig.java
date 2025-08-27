package com.example.bill.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.bill.mapper")
public class MyBatisConfig {
}
