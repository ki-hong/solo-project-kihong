package com.codestates.soloprojectkihong.api.v1.service;

import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member saveMember(Member member){

        return memberRepository.save(member);
    }

    public Page<Member> searchMember(String companyType, String companyLocation, Pageable pageable){
        Page<Member> members =memberRepository.findByType(companyType,companyLocation,pageable);
        return members;
    }
}
