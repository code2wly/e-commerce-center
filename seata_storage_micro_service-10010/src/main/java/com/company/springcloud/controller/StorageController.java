package com.company.springcloud.controller;

import com.company.springcloud.entity.Result;
import com.company.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    private StorageService storageService;

    //扣减库存
    @PostMapping("/storage/reduce")
    public Result reduce(Long productId, Integer nums) {
        storageService.reduce(productId, nums);
        return Result.success("扣减库存成功 ok", null);
    }
}
