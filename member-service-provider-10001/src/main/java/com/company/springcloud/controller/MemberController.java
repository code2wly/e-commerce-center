package com.company.springcloud.controller;

import com.company.springcloud.entity.Member;
import com.company.springcloud.entity.Result;
import com.company.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class MemberController {
    @Resource
    private MemberService memberService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/member/query/{id}")
    public Result query(@PathVariable("id") Long id) {
        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }

        Member member = memberService.queryMemberById(id);
        if (member != null) {
            return Result.success("查询会员成功，端口号：" + serverPort, member);
        } else {
            return Result.error("402", "ID=" + id + "不存在");
        }
    }

    @PostMapping("/member/save")
    public Result save(@RequestBody Member member) {
        log.info("-- service-provider member：" + member);
        int affected = memberService.save(member);
        if (affected > 0) { //说明添加成功
            return Result.success("添加会员成功", affected);
        } else {
            return Result.error("401", "添加会员失败");
        }
    }
}
