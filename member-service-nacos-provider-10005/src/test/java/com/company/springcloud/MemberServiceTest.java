package com.company.springcloud;


import com.company.springcloud.entity.Member;
import com.company.springcloud.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class MemberServiceTest {

    //装配MemberService
    @Resource
    private MemberService memberService;

    @Test
    public void queryMemberById() {
        Member member = memberService.queryMemberById(1L);
        log.info("--查询结果：" + member);
    }

    @Test
    public void save() {
        Member member =
                new Member(null, "狐狸精", "123", "13000000000", "hlj@sohu.com", 1);
        int result = memberService.save(member);
        log.info("--受影响的行数：" + result);
    }
}
