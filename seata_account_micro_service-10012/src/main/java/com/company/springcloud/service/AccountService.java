package com.company.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface AccountService {
    void reduce(@RequestParam("userId") Long userId, @RequestParam("money") Integer money);
}
