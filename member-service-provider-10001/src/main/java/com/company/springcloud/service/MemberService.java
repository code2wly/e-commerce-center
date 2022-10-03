package com.company.springcloud.service;

import com.company.springcloud.entity.Member;

public interface MemberService {
    Member queryMemberById(Long id);

    int save(Member member);
}
