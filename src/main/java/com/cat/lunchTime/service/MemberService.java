package com.cat.lunchTime.service;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.dto.CreateMember;
import com.cat.lunchTime.dto.EditUser;
import com.cat.lunchTime.dto.MemberDetailDto;
import com.cat.lunchTime.dto.MemberDto;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.entity.RetiredMember;
import com.cat.lunchTime.exception.MemberException;
import com.cat.lunchTime.repository.MemberRepository;
import com.cat.lunchTime.repository.RetiredMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.cat.lunchTime.exception.MemberErrorCode.*;

@Service

@RequiredArgsConstructor
public class MemberService {

    // @Injection @Autowired 서비스 테스트가 어려웠다.
    // @RequiredArgsConstructor 자동으로 생성자를 만들어준다.
    private final MemberRepository memberRepository;
    private final RetiredMemberRepository retiredMemberRepository;
    @Transactional
    public CreateMember.Response createMember(CreateMember.Request request){
            validateCreateUserRequest(request);


            // business logic start
            Member userInfo = Member.builder()
                    .userId(request.getUserId())
                    .userPw(request.getUserPw())
                    .name(request.getName())
                    .age(request.getAge())
                    .experienceYears(request.getExperienceYears())
                    .jobType(request.getJobType())
                    .foodCountry(request.getFoodCountry())
                    .statusCode(StatusCode.EMPLOYED)
                    .build();
            memberRepository.save(userInfo);
            return CreateMember.Response.fromEntity(userInfo);
    }

    private void validateCreateUserRequest(CreateMember.Request request) {
        // ctrl + alt + v 변수로 refactor 할 수 있다.
        memberRepository.findByUserId(request.getUserId()).ifPresent((developer) -> {
            throw new MemberException(DUPLICATED_MEMBER_ID);
        });
    }


    public List<MemberDto> getAllEmployedMembers() {
        return memberRepository.findMemberByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
    public MemberDetailDto getMemberDetail(String memberId) {
        return memberRepository.findByUserId(memberId)
                .map(MemberDetailDto::fromEntity)
                .orElseThrow(() -> new MemberException(INVALID_REQUEST));
    }


    public MemberDetailDto editMember(String memberId, EditUser.Request request) {
        validateEditeUserRequest(memberId, request);

        Member user = memberRepository.findByUserId(memberId).orElseThrow(
                () -> new MemberException(NO_MEMBER_ID)
        );
        user.setJobType(request.getJobType());
        user.setFoodCountry(request.getFoodCountry());
        user.setAge(request.getAge());

        return MemberDetailDto.fromEntity(user);
    }

    private void validateEditeUserRequest(String memberId, EditUser.Request request) {
        Member user = memberRepository.findByUserId(memberId).orElseThrow(
                () -> new MemberException(NO_MEMBER_ID)
        );


    }

    @Transactional
    public MemberDetailDto deleteMember(String memberId) {
        // 1. EMPLOYED -> RETIRED
        Member member = memberRepository.findByUserId(memberId).orElseThrow(() -> new MemberException(NO_MEMBER_ID));
        member.setStatusCode(StatusCode.RETIRED);

        if (member != null) throw new MemberException(NO_MEMBER_ID);
        
        // 2. save into RetiredMember
        RetiredMember retiredMember = RetiredMember.builder()
                .userId(memberId)
                .name(member.getName())
                .build();

        retiredMemberRepository.save(retiredMember);
        return MemberDetailDto.fromEntity(member);
    }
}
