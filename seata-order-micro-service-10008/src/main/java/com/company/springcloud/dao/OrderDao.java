package com.company.springcloud.dao;

import com.company.springcloud.enitiy.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    //新建订单
    void save(Order order);

    //修改订单状态
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
