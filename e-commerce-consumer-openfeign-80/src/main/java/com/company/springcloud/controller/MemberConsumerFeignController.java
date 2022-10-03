package com.company.springcloud.controller;

import com.company.springcloud.entity.Result;
import com.company.springcloud.service.MemberFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberConsumerFeignController {
    @Resource
    private MemberFeignService memberFeignService;

    @GetMapping("/consumer/member/query/{id}")
    public Result query(@PathVariable("id") Long id) {
        return memberFeignService.query(id);
    }
}
