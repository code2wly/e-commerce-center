package com.company.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //排除数据源的自动配置
@EnableDiscoveryClient //开启服务发现
@EnableFeignClients //开启远程调用
public class SeataStorageMicroServiceMain10010 {
    public static void main(String[] args) {
        SpringApplication.run(SeataStorageMicroServiceMain10010.class, args);
    }
}
