package com.codestates.soloprojectkihong.restdocs;

import com.codestates.soloprojectkihong.api.v1.controller.MemberController;
import com.codestates.soloprojectkihong.api.v1.dto.MemberDto;
import com.codestates.soloprojectkihong.api.v1.entity.Member;
import com.codestates.soloprojectkihong.api.v1.mapper.MemberMapper;
import com.codestates.soloprojectkihong.api.v1.service.MemberService;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Arrays;
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
                .name("저쩌구")
                .companyLocation("003")
                .companyName("강원")
                .companyType("004")
                .password("1234")
                .sex('f').build();
        List<Member> list = List.of(member1);
        Page<Member> members = new PageImpl<>(list);
        MemberDto.ResponseDto member2 = MemberDto.ResponseDto.builder()
                .name("저쩌구")
                .companyLocation("003")
                .companyName("강원")
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
                                parameterWithName("companyLocation").description("지역 코드"),
                                parameterWithName("companyType").description("업종 코드")
                        ),
                        responseFields(
                                List.of(
                                        fieldWithPath("data.[]").type(JsonFieldType.ARRAY).description("결과 데이터"),
                                        fieldWithPath("data.[].name").type(JsonFieldType.STRING).description("이름"),
                                        fieldWithPath("data.[].companyLocation").type(JsonFieldType.STRING).description("지역 코드"),
                                        fieldWithPath("data.[].companyType").type(JsonFieldType.STRING).description("업무 코드"),
                                        fieldWithPath("data.[].companyName").type(JsonFieldType.STRING).description("회사이름"),
                                        fieldWithPath("data.[].sex").type(JsonFieldType.STRING).description("성"),
                                        fieldWithPath("pageInfo.size").type(JsonFieldType.NUMBER).description("페이지 당 회원 수"),
                                        fieldWithPath("pageInfo.page").type(JsonFieldType.NUMBER).description("페이지"),
                                        fieldWithPath("pageInfo.totalElements").type(JsonFieldType.NUMBER).description("총 회원 수"),
                                        fieldWithPath("pageInfo.totalPages").type(JsonFieldType.NUMBER).description("총 페이지 수")
                                )
                        )
                ));

//        List list = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data");
//
//        assertThat(list.size(), is(1));
    }
}
