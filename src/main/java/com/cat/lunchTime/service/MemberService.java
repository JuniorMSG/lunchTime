package com.cat.lunchTime.service;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.dto.CreateMember;
import com.cat.lunchTime.dto.EditMember;
import com.cat.lunchTime.dto.MemberDetailDto;
import com.cat.lunchTime.dto.MemberDto;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.entity.RetiredMember;
import com.cat.lunchTime.exception.MemberException;
import com.cat.lunchTime.repository.MemberRepository;
import com.cat.lunchTime.repository.RetiredMemberRepository;
import com.cat.lunchTime.type.MemberLevel;
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
        validateCreateMemberRequest(request);
        request.getMemberLevel().validateExperienceYears(
                request.getExperienceYears()
        );
        // business logic start
        return CreateMember.Response.fromEntity(
                memberRepository.save(createMemberFromRequest(request))
        );
    }

    private Member createMemberFromRequest(CreateMember.Request request) {
        return Member.builder()
                .memberId(request.getMemberId())
                .password(request.getPassword())
                .name(request.getName())
                .age(request.getAge())
                .experienceYears(request.getExperienceYears())
                .memberLevel(request.getMemberLevel())
                .jobType(request.getJobType())
                .foodCountry(request.getFoodCountry())
                .statusCode(StatusCode.EMPLOYED)
                .build();
    }

    private void validateCreateMemberRequest(CreateMember.Request request) {
        // ctrl + alt + v 변수로 refactor 할 수 있다.
        request.getMemberLevel().validateExperienceYears(
                request.getExperienceYears()
        );
        memberRepository.findByMemberId(request.getMemberId()).ifPresent((developer) -> {
            throw new MemberException(DUPLICATED_MEMBER_ID);
        });
    }


    public List<MemberDto> getAllEmployedMembers() {
        return memberRepository.findMemberByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream().map(MemberDto::fromEntity)
                .collect(Collectors.toList());
    }
    public MemberDetailDto getMemberDetail(String memberId) {
        return MemberDetailDto.fromEntity(getMemberByMemberId(memberId));
    }

    private Member getMemberByMemberId(String memberId){
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberException(NO_MEMBER_ID)
        );
    }

    public MemberDetailDto editMember(String memberId, EditMember.Request request) {
        return MemberDetailDto.fromEntity(
                getUpdateMemberFromRequest(request, getMemberByMemberId(memberId))
        );
    }
    private Member getUpdateMemberFromRequest(EditMember.Request request, Member member) {
        member.setJobType(request.getJobType());
        member.setFoodCountry(request.getFoodCountry());
        member.setAge(request.getAge());
        return member;
    }

    @Transactional
    public MemberDetailDto deleteMember(String memberId) {
        // 1. EMPLOYED -> RETIRED
        Member member = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new MemberException(NO_MEMBER_ID));
        member.setStatusCode(StatusCode.RETIRED);

        // 2. save into RetiredMember
        RetiredMember retiredMember = RetiredMember.builder()
                .memberId(memberId)
                .name(member.getName())
                .build();

        retiredMemberRepository.save(retiredMember);
        return MemberDetailDto.fromEntity(member);
    }
}
