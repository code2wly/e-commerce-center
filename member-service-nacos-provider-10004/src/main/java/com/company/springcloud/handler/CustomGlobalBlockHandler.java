package com.company.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.company.springcloud.entity.Result;

/**
 * 全局限流处理类
 */
public class CustomGlobalBlockHandler {

    public static Result handleMethod1(BlockException exception) {
        return Result.error("400", "自定义的限流处理方法 -> handleMethod1()");
    }
    public static Result handleMethod2(BlockException exception) {
        return Result.error("401", "自定义的限流处理方法 -> handleMethod2()");
    }
}
