package com.codestates.soloprojectkihong.restdocs;

import com.codestates.soloprojectkihong.api.v1.controller.MemberController;
import com.codestates.soloprojectkihong.api.v1.dto.MemberDto;
import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.mapper.MemberMapper;
import com.codestates.soloprojectkihong.api.v1.service.MemberService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    @MockBean
    private MemberMapper memberMapper;

    @Test
    void getMembersTest() throws Exception {
        // given
        Member member1 = Member.builder()
                .name("?????????")
                .companyLocation("003")
                .companyName("??????")
                .companyType("004")
                .password("1234")
                .sex('f').build();
        List<Member> list = List.of(member1);
        Page<Member> members = new PageImpl<>(list);
        MemberDto.ResponseDto member2 = MemberDto.ResponseDto.builder()
                .name("?????????")
                .companyLocation("003")
                .companyName("??????")
                .companyType("004")
                .sex('f').build();
        List<MemberDto.ResponseDto> response = List.of(member2);

        // Stubbing by Mockito
        given(memberService.searchMember(Mockito.anyString(), Mockito.anyString(), Mockito.any())).willReturn(members);
        given(memberMapper.memberToMemberResponses(Mockito.any())).willReturn(response);


        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("companyLocation", "003");
        queryParams.add("companyType", "004");

        // when
        ResultActions actions = mockMvc.perform(
                get("/v1/members").params(queryParams)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        // then
        actions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andDo(document("get-member",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestParameters(
                                parameterWithName("companyLocation").description("?????? ??????"),
                                parameterWithName("companyType").description("?????? ??????")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.[]").type(JsonFieldType.ARRAY).description("?????? ?????????"),
                                        fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("??????"),
                                        fieldWithPath("data.[].companyLocation").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.[].companyType").type(JsonFieldType.STRING).description("?????? ??????"),
                                        fieldWithPath("data.[].companyName").type(JsonFieldType.STRING).description("????????????"),
                                        fieldWithPath("data.[].sex").type(JsonFieldType.STRING).description("???"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("????????? ??? ?????? ???"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("?????????"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("??? ?????? ???"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("??? ????????? ???")
                                )
                        )
                ));

//        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
//
//        assertThat(list.size(), is(1));
    }
}
