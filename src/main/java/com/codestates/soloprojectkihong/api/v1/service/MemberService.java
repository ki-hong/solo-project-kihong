package com.codestates.soloprojectkihong.api.v1.service;

import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.repository.MemberRepository;
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

    public List<Member> searchMember(String companyType, String companyLocation){
        List<Member> members =memberRepository.findByType(companyType,companyLocation);
        return members;
    }
}
