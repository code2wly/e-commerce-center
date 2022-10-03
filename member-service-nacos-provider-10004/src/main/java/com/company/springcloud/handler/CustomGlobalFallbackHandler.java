package com.company.springcloud.handler;

import com.company.springcloud.entity.Result;

/**
 * 全局fallback处理类
 */
public class CustomGlobalFallbackHandler {
    public static Result fallbackHandlerMethod1(Throwable e) {
        return Result.error("402", "java异常信息：" + e.getMessage());
    }

    public static Result fallbackHandlerMethod2(Throwable e) {
        return Result.error("403", "业务异常信息：" + e.getMessage());
    }
}
