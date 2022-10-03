package com.company.springcloud.controller;

import com.company.springcloud.entity.Result;
import com.company.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
public class AccountController {
    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @PostMapping("/account/reduce")
    public Result result(@RequestParam("userId") Long userId, @RequestParam("money") Integer money) {
        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        accountService.reduce(userId, money);
        return Result.success("200", "扣减账户余额 OK");
    }
}
