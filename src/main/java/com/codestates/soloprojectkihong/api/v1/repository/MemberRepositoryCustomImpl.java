package com.codestates.soloprojectkihong.api.v1.repository;

import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.entity.QMember;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final QMember member=new QMember("member");

    public MemberRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Member> findByType(String companyType, String companyLocation, Pageable pageable) {

        List<Member> members=  queryFactory
                .selectFrom(member)
                .where(eqCompanyType(companyType),
                        eqCompanyLocation(companyLocation))
                .offset(pageable.getOffset()) // (1)
                .limit(pageable.getPageSize()) // (2)
                .fetch();
        return new PageImpl<>(members, pageable, members.size());
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
