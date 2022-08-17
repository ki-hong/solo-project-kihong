package com.codestates.soloprojectkihong.api.v1.mapper;


import com.codestates.soloprojectkihong.api.v1.dto.MemberDto;
import com.codestates.soloprojectkihong.api.v1.entity.Member;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberPostToMember(MemberDto.Post post);
    List<MemberDto.ResponseDto> memberToMemberResponses(List<Member> members);
}
