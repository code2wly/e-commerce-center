package com.company.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.company.springcloud.entity.Member;
import com.company.springcloud.entity.Result;
import com.company.springcloud.handler.CustomGlobalBlockHandler;
import com.company.springcloud.handler.CustomGlobalFallbackHandler;
import com.company.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class MemberController {
    @Resource
    private MemberService memberService;

    private static int num = 0; //计速器

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/member/query/{id}")
    public Result query(@PathVariable("id") Long id) {
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        log.info("-- query()方法 当前线程id=" + Thread.currentThread().getId() + " 时间：" + new Date());
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

    //流量控制实例-关联
    @GetMapping("/t1")
    public Result t1() {
        return Result.success("t1()成功执行了...");
    }

    @GetMapping("/t2")
    public Result t2() {
        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("-- t2()方法 当前线程id=" + Thread.currentThread().getId() + " 时间：" + new Date());
        return Result.success("t2()成功执行了...");
    }

    //熔断降级实例-慢调用比例
    @GetMapping("/t3")
    public Result t3() {
        //暂停300毫秒线程
        try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }
        return Result.success("t3()成功执行了...");
    }

    //熔断降级实例-异常比例
    @GetMapping("/t4")
    public Result t4() {
        //异常比例 50%
        if ((++num) % 2 == 0) {
            System.out.println(3 / 0);
        }
        log.info("-- t4()方法 当前线程id=" + Thread.currentThread().getId());
        return Result.success("t4()成功执行了...");
    }

    //熔断降级实例-异常数
    @GetMapping("/t5")
    public Result t5() {
        //异常数 10（这里设置大于 6，需要留出几次做测试和加入簇点链路）
        if (++num <= 10) {
            System.out.println(3 / 0);
        }
        log.info("-- t5()方法 当前线程id=" + Thread.currentThread().getId());
        return Result.success("t5()成功执行了...");
    }

    //使用自定义全局限流处理类，显示限流信息
    @GetMapping("/t6")
    @SentinelResource( value = "t6",
            blockHandlerClass = CustomGlobalBlockHandler.class,
            blockHandler = "handleMethod1")
    public Result t6(){
        log.info("-- t6()方法 当前线程id=" + Thread.currentThread().getId());
        return Result.success("t6()成功执行了...");
    }

    //使用自定义全局fallback处理类，显示异常信息
    //使用 exceptionsToIgnore 忽略某个异常
    @GetMapping("/t7")
    @SentinelResource( value = "t7",
            fallbackClass = CustomGlobalFallbackHandler.class,
            fallback = "fallbackHandlerMethod1",
            exceptionsToIgnore = {RuntimeException.class})
    public Result t7(){
        //发生java异常
        if (++num % 3 == 0){
            throw new RuntimeException("num的值异常 num="+num);
        }
        log.info("-- t7()方法 当前线程id=" + Thread.currentThread().getId());
        return Result.success("t7()成功执行了...");
    }

    //热点key限流-处理异常信息
    public Result newsBlockHandler(String id, String type, BlockException blockException){

        return Result.success("查询id="+id+" 的新闻 触发了热点key限流保护...");
    }

    //热点key限流
    @GetMapping("/news")
    @SentinelResource(value = "news",blockHandler = "newsBlockHandler")
    public Result queryNews(@RequestParam(value = "id",required = false) String id,
                            @RequestParam(value = "type",required = false) String type){

        log.info("--从缓存或数据库中查询该新闻...");
        return Result.success("返回id="+id+" 的新闻...");
    }

}
