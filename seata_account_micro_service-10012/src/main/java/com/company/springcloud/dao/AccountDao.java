package com.company.springcloud.dao;

import org.apache.ibatis.annotations.Param;

public interface AccountDao {
    void reduce(@Param("userId") Long userId, @Param("money") Integer money);
}