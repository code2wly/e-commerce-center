package com.company.springcloud.controller;

import com.company.springcloud.entity.Member;
import com.company.springcloud.entity.Result;
import com.company.springcloud.service.MemberOpenFeignService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class MemberController {
    @Value("${server.port}")
    private String serverPort;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private MemberOpenFeignService memberOpenFeignService;

    @Value("${service-url.nacos-user-service}")
    private String serverURL;

    @GetMapping("/consumer/member/query/{id}")
    public Result<Member> query(@PathVariable("id") Long id) {
        //getForObject：返回对象为响应体中数据转化成的对象，基本可以理解为json
        return restTemplate.getForObject(serverURL + "/member/query/" + id, Result.class);
    }

    //使用 openFeign 远程调用接口
    @GetMapping("/consumer/openfeign/member/query/{id}")
    public Result<Member> queryByOpenFeign(@PathVariable("id") Long id){
        return memberOpenFeignService.query(id);
    }
}