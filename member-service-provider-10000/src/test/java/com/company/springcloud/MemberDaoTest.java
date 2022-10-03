package com.company.springcloud;

import com.company.springcloud.dao.MemberDao;
import com.company.springcloud.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class MemberDaoTest {
    //装配MemberDao
    @Resource
    private MemberDao memberDao;

    @Test
    public void queryMemberById() {
        Member member = memberDao.queryMemberById(1L);
        log.info("--查询结果：" + member);
    }

    @Test
    public void save() {
        Member member =
                new Member(null, "红孩儿", "123", "17822223333", "hong@163.com", 1);
        int result = memberDao.save(member);
        log.info("--受影响的行数：" + result);
    }
}
