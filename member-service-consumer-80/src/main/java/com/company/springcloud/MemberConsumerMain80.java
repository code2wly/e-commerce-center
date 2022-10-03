package com.company.springcloud;

import com.company.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //启用服务发现
//@RibbonClient(name = "MEMBER-SERVICE-PROVIDER",configuration= MySelfRule.class)
public class MemberConsumerMain80 {
    public static void main(String[] args) {
        SpringApplication.run(MemberConsumerMain80.class, args);
    }
}
