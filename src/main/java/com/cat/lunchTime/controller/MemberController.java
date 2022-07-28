package com.cat.lunchTime.controller;

import com.cat.lunchTime.dto.CreateMember;
import com.cat.lunchTime.dto.EditUser;
import com.cat.lunchTime.dto.MemberDto;
import com.cat.lunchTime.dto.MemberDetailDto;
import com.cat.lunchTime.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * packageName    : com.cat.lunchTime.controller
 * fileName       : LunchTimeGroupController
 * author         : Cute Cat
 * date           : 2022-07-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-10        Cute Cat       최초 생성
 */

@Slf4j
@RestController // Bean 을 RestController 으로 등록한다.
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members")
    public List<MemberDto> getAllMembers() {
        log.info("Get /getGroupInfo HTTP/1.1 ");
        return memberService.getAllEmployedMembers();
    }

    @GetMapping("/member/{memberId}")
    public MemberDetailDto getMemberDetail(
            @PathVariable final String memberId
    ) {
        // 중괄호로 넣게되면 pass variable으로 값을 가져 올 수 있게된다.
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return memberService.getMemberDetail(memberId);
    }

    // Put 데이터 전체 수정
    // Fetch 데이터 일부 수정
    @PutMapping("/member/{memberId}")
    public MemberDetailDto editMember(
            @PathVariable final String memberId,
            @Valid @RequestBody EditUser.Request request

    ) {
        log.info("request : {}", request);
        return memberService.editMember(memberId, request);
    }


    // ctrl + alt + l 자동정렬
    // ctrl + alt + o 클래스 임포트 / 삭제
    @PostMapping("/create-member")
    public CreateMember.Response createMember(
            @Valid @RequestBody CreateMember.Request request
    ) {
        log.info("request : {}", request);
        return memberService.createMember(request);
    }


    @DeleteMapping("/member/{memberId}")
    public MemberDetailDto deleteMember(
            @PathVariable final String memberId
    ) {
        log.info("request : {}", memberId);
        // Transaction
        // 1. EMPLOYER -> RETIRED
        // 2. SAVE INTO RetiredMember
        return memberService.deleteMember(memberId);
    }

}
