package com.cat.lunchTime.service;

import com.cat.lunchTime.dto.CreateMember;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.exception.MemberErrorCode;
import com.cat.lunchTime.exception.MemberException;
import com.cat.lunchTime.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.cat.lunchTime.type.FoodCountry.Korea;
import static com.cat.lunchTime.type.JobType.Developer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

// Bean등을 다 생성해준다.
@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    // @SpringBootTest 입력시 모든 Bean을 사용 할 수 있다.
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private final CreateMember.Request defaultCreateRequest = CreateMember.Request.builder()
            .jobType(Developer)
            .foodCountry(Korea)
            .experienceYears(12)
            .age(37)
            .userId("milk9999")
            .userPw("12341234")
            .name("DevCat").build();

    @Test
    void createMemberTest_success(){
        //given
        given(memberRepository.findByUserId(anyString()))
                .willReturn(Optional.empty());
        ArgumentCaptor<Member> captor = ArgumentCaptor.forClass(Member.class);

        //when
        CreateMember.Response response = memberService.createMember(defaultCreateRequest);

        //then
        verify(memberRepository, times(1))
                .save(captor.capture());
        Member saveMember = captor.getValue();
        assertEquals(Developer, saveMember.getJobType());
        assertEquals(Korea, saveMember.getFoodCountry());
        assertEquals(12, saveMember.getExperienceYears());


    }

    @Test
    void createMemberTest_failed_with_invalid_experience() {
        //given
        given(memberRepository.findByUserId(anyString()))
                .willReturn(Optional.empty());

        //when
        MemberException exception =
                assertThrows(MemberException.class, () -> memberService.createMember(defaultCreateRequest));
        //then
        assertEquals(MemberErrorCode.DUPLICATED_MEMBER_ID, exception.getUserErrorCode());
    }
}