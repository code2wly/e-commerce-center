package com.company.springcloud.controller;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * 资源清洗
 */
@Component
public class CustomUrlCleaner implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        //校验一个String类型的变量是否为空
        if (StringUtils.isBlank(originUrl)) {
            return originUrl;
        }
        //校验一个String类型的变量是否以"/member/query/"开头
        if (originUrl.startsWith("/member/query/")) {
            return "/member/query/*";
        }
        return originUrl;
    }
}