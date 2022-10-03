package com.company.springcloud.controller;


import com.company.springcloud.enitiy.Order;
import com.company.springcloud.entity.Result;
import com.company.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 1. 前端以json格式来发送请求，需要使用@RequestBody才能将数据封装到对应的bean, 同时保证http的请求头的 content-type是对应
     * 2. 前端以表单形式来发送请求，则不需要使用@RequestBody, 才会进行对象参数封装, 同时保证http的请求头的 content-type是对应
     */
    @GetMapping("/order/save")
    public Result save(Order order) {
        orderService.save(order);
        return Result.success("订单创建成功", null);
    }
}
