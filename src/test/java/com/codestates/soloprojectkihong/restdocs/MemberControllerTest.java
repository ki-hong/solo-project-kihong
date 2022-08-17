package com.codestates.soloprojectkihong.restdocs;

import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.mapper.MemberMapper;
import com.codestates.soloprojectkihong.api.v1.service.MemberService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    void getMembersTest() throws Exception {
        // given
        Member member1 = Member.builder()
                .name("어쩌구")
                .companyLocation("001")
                .companyName("광주")
                .companyType("002")
                .sex('m')
                .password("1234").build();
        Member member2 = Member.builder()
                .name("저쩌구")
                .companyLocation("003")
                .companyName("강원")
                .companyType("004")
                .sex('f')
                .password("1234").build();
        List<Member> members = List.of(member1,member2);

        // Stubbing by Mockito
        given(memberService.searchMember(Mockito.anyString(), Mockito.anyString())).willReturn(members);


        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("companyLocation", "003");
        queryParams.add("companyType", "004");

        // when
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.get("/v1/members").params(queryParams).accept(MediaType.APPLICATION_JSON));

        // then
        MvcResult result = actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andReturn();

        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");

        assertThat(list.size(), is(1));
    }
}
