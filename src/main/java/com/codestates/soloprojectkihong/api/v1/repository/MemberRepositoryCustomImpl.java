package com.codestates.soloprojectkihong.api.v1.repository;

import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QMember member;

    @Override
    public List<Member> findByType(String companyType, String companyLocation) {

        return queryFactory
                .selectFrom(member)
                .where(eqCompanyType(companyType),
                        eqCompanyLocation(companyLocation))
                .fetch();
    }

    private BooleanExpression eqCompanyType(String companyType) {
        if (companyType.isEmpty()) {
            return null;
        }
        return member.companyType.eq(companyType);
    }

    private BooleanExpression eqCompanyLocation(String companyLocation) {
        if (companyLocation.isEmpty()) {
            return null;
        }
        return member.companyLocation.eq(companyLocation);
    }


}
