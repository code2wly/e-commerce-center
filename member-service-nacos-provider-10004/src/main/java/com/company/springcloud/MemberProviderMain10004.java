package com.company.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MemberProviderMain10004 {
    public static void main(String[] args) {
        SpringApplication.run(MemberProviderMain10004.class, args);
    }
}
