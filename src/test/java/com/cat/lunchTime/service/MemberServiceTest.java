package com.cat.lunchTime.service;

import com.cat.lunchTime.dto.CreateMember;
import com.cat.lunchTime.dto.MemberDetailDto;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.exception.MemberException;
import com.cat.lunchTime.repository.MemberRepository;
import com.cat.lunchTime.type.JobType;
import com.cat.lunchTime.type.MemberLevel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.cat.lunchTime.code.StatusCode.EMPLOYED;
import static com.cat.lunchTime.constant.MemberConstant.MAX_JUNIOR_EXPERIENCE_YEARS;
import static com.cat.lunchTime.constant.MemberConstant.MIN_SENIOR_EXPERIENCE_YEARS;
import static com.cat.lunchTime.exception.MemberErrorCode.DUPLICATED_MEMBER_ID;
import static com.cat.lunchTime.exception.MemberErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;
import static com.cat.lunchTime.type.FoodCountry.Korea;
import static com.cat.lunchTime.type.JobType.Designer;
import static com.cat.lunchTime.type.JobType.Developer;
import static com.cat.lunchTime.type.MemberLevel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// Bean등을 다 생성해준다.
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private final Member defaultMember = Member.builder()
            .memberLevel(SENIOR)
            .jobType(Designer)
            .foodCountry(Korea)
            .experienceYears(12)
            .statusCode(EMPLOYED)
            .name("DevCat")
            .age(12)
            .build();

    private CreateMember.Request getCreateRequest(
            MemberLevel memberLevel,
            JobType jobType,
            Integer experienceYears
    ) {
        System.out.println(memberLevel);
        return CreateMember.Request.builder()
                .memberLevel(memberLevel)
                .jobType(jobType)
                .foodCountry(Korea)
                .experienceYears(experienceYears)
                .memberId("memberId")
                .name("name")
                .age(40)
                .build();
    }

    @Test
    public void testSomething() {
        //given
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultMember));

        //when
        MemberDetailDto memberDetailDto = memberService.getMemberDetail("memberId");

        //then
        assertEquals(SENIOR, memberDetailDto.getMemberLevel());
        assertEquals(Designer, memberDetailDto.getJobType());
        assertEquals(12, memberDetailDto.getExperienceYears());
    }

    @Test
    void createDeveloperTest_success() {
        //given
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());
        given(memberRepository.save(any()))
                .willReturn(defaultMember);
        ArgumentCaptor<Member> captor =
                ArgumentCaptor.forClass(Member.class);

        //when
        memberService.createMember(getCreateRequest(SENIOR, Developer, MIN_SENIOR_EXPERIENCE_YEARS));

        //then
        verify(memberRepository, times(1))
                .save(captor.capture());
        Member savedMember = captor.getValue();
        assertEquals(SENIOR, savedMember.getMemberLevel());
        assertEquals(Developer, savedMember.getJobType());
        assertEquals(12, savedMember.getExperienceYears());
    }

    @Test
    void createDeveloperTest_fail_with_unmatched_level() {
        //given
        //when
        //then
        MemberException memberException = assertThrows(MemberException.class,
                () -> memberService.createMember(
                        getCreateRequest(JUNIOR, Designer,
                                MAX_JUNIOR_EXPERIENCE_YEARS + 1)
                )
        );
        assertEquals(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED,
                memberException.getMemberErrorCode()
        );

        memberException = assertThrows(MemberException.class,
                () -> memberService.createMember(
                        getCreateRequest(JUNGNIOR, Developer,
                                MIN_SENIOR_EXPERIENCE_YEARS + 1)
                )
        );
        assertEquals(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED,
                memberException.getMemberErrorCode()
        );

        memberException = assertThrows(MemberException.class,
                () -> memberService.createMember(
                        getCreateRequest(SENIOR, Developer,
                                MIN_SENIOR_EXPERIENCE_YEARS - 1)
                )
        );
        assertEquals(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED,
                memberException.getMemberErrorCode()
        );

    }

    @Test
    void createDeveloperTest_failed_with_duplicated() {
        //given
        given(memberRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(defaultMember));

        //when
        //then
        MemberException memberException = assertThrows(MemberException.class,
                () -> memberService.createMember(
                        getCreateRequest(SENIOR, Developer, MIN_SENIOR_EXPERIENCE_YEARS)
                )
        );

        assertEquals(DUPLICATED_MEMBER_ID, memberException.getMemberErrorCode());
    }

}