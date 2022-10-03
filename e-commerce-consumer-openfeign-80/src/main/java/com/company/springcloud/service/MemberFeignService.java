package com.company.springcloud.service;

import com.company.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "MEMBER-SERVICE-PROVIDER") //Eureka Server服务提供方注册的名称
public interface MemberFeignService {
    //服务提供方的 controller 方法名称
    @GetMapping("/member/query/{id}")
    public Result query(@PathVariable("id") Long id);
}
