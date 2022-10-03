package com.company.springcloud.service;

import com.company.springcloud.entity.Result;
import org.springframework.stereotype.Component;

/**
 * 全局fallback处理类
 */
@Component
public class MemberFeignFallbackService implements MemberOpenFeignService{
    @Override
    public Result query(Long id) {
        return Result.error("500","被调用服务异常，启用熔断降级，快速返回结果，防止线程堆积");
    }
}
