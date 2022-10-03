package com.company.springcloud.service;

import com.company.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "seata-account-micro-service") //Nacos Server注册中心注册的服务名
public interface AccountService {
    /**
     * 扣减账户余额
     */
    @PostMapping("/account/reduce")
    public Result reduce(@RequestParam("userId") Long userId, @RequestParam("money") Integer money);
}