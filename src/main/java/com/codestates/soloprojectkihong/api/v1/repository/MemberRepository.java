package com.codestates.soloprojectkihong.api.v1.repository;


import com.codestates.soloprojectkihong.api.v1.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long>,MemberRepositoryCustom {
}
