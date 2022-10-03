package com.company.springcloud.service;

import com.company.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(value = "seata-storage-micro-service") //Nacos Server注册中心注册的服务名
public interface StorageService {
    /**
     * 扣减库存
     */
    @PostMapping("/storage/reduce")
    public Result reduce(@RequestParam("productId") Long productId, @RequestParam("nums") Integer nums);
}
