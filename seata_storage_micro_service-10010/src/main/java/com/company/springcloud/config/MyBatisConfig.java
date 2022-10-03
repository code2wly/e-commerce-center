package com.company.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.company.springcloud.dao"}) //扫描 Mapper 接口所在的包
public class MyBatisConfig {
}
