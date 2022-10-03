package com.company.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication //SpringBoot工程运行后初始化Spring容器，扫描该类所在包加载bean
@EnableEurekaClient //Eureka Client
public class MemberProviderMain10000 {
    public static void main(String[] args) {
        SpringApplication.run(MemberProviderMain10000.class, args);
    }
}
