package com.codestates.soloprojectkihong.api.v1.repository;

import com.codestates.soloprojectkihong.api.v1.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberRepositoryCustom {
    Page<Member> findByType(String companyType, String companyLocation, Pageable pageable);
}
