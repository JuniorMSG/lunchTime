package com.cat.lunchTime.controller;

import com.cat.lunchTime.dto.MemberDto;
import com.cat.lunchTime.service.MemberService;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 특정 클래스 + Controller Advise등등 기반이 되는 Bean 까지 올라간다.
@WebMvcTest(MemberController.class)
@MockBean(JpaMetamodelMappingContext.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private MemberService memberService;

    protected MediaType contentType =
            new MediaType(MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Test
    void getAllMembers() throws Exception {
        MemberDto memberDev  = MemberDto.builder()
                .foodCountry(FoodCountry.Korea)
                .jobType(JobType.Developer)
                .name("DevCat")
                .build();
        MemberDto memberDesign  = MemberDto.builder()
                .jobType(JobType.Designer)
                .foodCountry(FoodCountry.Korea)
                .name("DesignCat1")
                .build();
        given(memberService.getAllEmployedMembers())
                .willReturn(Arrays.asList(memberDev, memberDesign));


        mockMvc.perform(get("/members").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(
                        jsonPath("$.[0].jobType",
                                is(JobType.Developer.name())))
                .andExpect(
                        jsonPath("$.[0].foodCountry",
                                is(FoodCountry.Korea.name())))
                .andExpect(
                        jsonPath("$.[1].jobType",
                                is(JobType.Designer.name())))
                .andExpect(
                        jsonPath("$.[1].foodCountry",
                                is(FoodCountry.Korea.name())));
    }
}