package com.company.springcloud.service.impl;


import com.company.springcloud.dao.OrderDao;
import com.company.springcloud.enitiy.Order;
import com.company.springcloud.service.AccountService;
import com.company.springcloud.service.OrderService;
import com.company.springcloud.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    /**
     * 1. @GlobalTransactional : 分布式全局事务控制  io.seata.spring.annotation包
     * 2. name = "seata-save-order" 名称，程序员自己指定,保证唯一即可
     * 3. rollbackFor = Exception.class 指定发送什么异常就回滚, 这里我们指定的是Exception.class 即 只要发生了异常就回滚
     */
    @GlobalTransactional(name = "seata-save-order", rollbackFor = Exception.class)
    public void save(Order order) {
        //后面我们如果需要打印日志
        log.info("====创建订单 start====");

        log.info("====本地生成订单 start====");
        //调用本地方法生成订单order
        orderDao.save(order);
        log.info("====本地生成订单 end====");

        log.info("====扣减库存 start====");
        //远程调用storage微服务扣减库存
        storageService.reduce(order.getProductId(), order.getNums());
        log.info("====扣减库存 end====");

        log.info("====扣减用户余额 start====");
        //远程调用account微服务扣减用户money
        accountService.reduce(order.getUserId(), order.getMoney());
        log.info("====扣减用户余额 end====");

        log.info("====本地修改订单状态 start====");
        //调用本地方法修改订单状态0->1
        orderDao.update(order.getUserId(), 0);
        log.info("====本地修改订单状态 end====");

        log.info("====创建订单 end====");
    }
}
