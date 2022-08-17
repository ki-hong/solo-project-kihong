package com.codestates.soloprojectkihong.api.v1.controller;

import com.codestates.soloprojectkihong.api.v1.dto.MemberDto;
import com.codestates.soloprojectkihong.api.v1.dto.MultiResponseDto;
import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.mapper.MemberMapper;
import com.codestates.soloprojectkihong.api.v1.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/members")
@Validated
public class MemberController {

    private MemberMapper mapper;
    private MemberService service;

    public MemberController(MemberMapper mapper, MemberService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping
    public ResponseEntity joinMember(@Valid @RequestBody MemberDto.Post post){


        return null;
    }


    @GetMapping
    public ResponseEntity getMemebers(@RequestParam String companyType, @RequestParam String companyLocation){
        List<Member> members = service.searchMember(companyType,companyLocation);
        return new ResponseEntity<>(new MultiResponseDto<>(mapper.memberToMemberResponses(members)), HttpStatus.OK);
    }

}
