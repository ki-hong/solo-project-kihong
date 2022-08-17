package com.codestates.soloprojectkihong.api.v1.repository;

import com.codestates.soloprojectkihong.api.v1.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findByType(String companyType, String companyLocation);
}
