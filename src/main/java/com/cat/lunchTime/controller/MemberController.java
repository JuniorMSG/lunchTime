package com.cat.lunchTime.controller;

import com.cat.lunchTime.dto.CreateMemberDTO;
import com.cat.lunchTime.dto.EditUser;
import com.cat.lunchTime.dto.MemberDetailDto;
import com.cat.lunchTime.dto.MemberDto;
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

    // MVC에서 C인 Controller 영역에선
    // 응답값을 잘 돌려주고 잘 받아주는게 주 역할임
    // 나머지는 Service Layer로 보내줘야함.

    // 서비스 레이어에선 우리는 당연하게 성공한다 라는 로직으로 접근해야함.
    // 문제가 생겼을땐 Exception을 던지면 어디선가(ExceptionHanler)에서 처리해준다!
    // 글로벌 예외처리 컨트롤 어드바이서를 꼭 만들어서 하면 좋다.

    // Refactoring
    // null을 의도적으로 처리하는 것 자체가 별로 좋지 않다.


    @GetMapping("/members")
    public List<MemberDto> getAllMembers() {
        log.info("Get /getGroupInfo HTTP/1.1 ");
        return memberService.getAllEmployedMembers();
    }

    @GetMapping("/member/{memberId}")
    public MemberDetailDto getMemberDetail(
            @PathVariable String memberId
    ) {
        // 중괄호로 넣게되면 pass variable으로 값을 가져 올 수 있게된다.
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return memberService.getMemberDetail(memberId);
    }

    // Put 데이터 전체 수정
    // Fetch 데이터 일부 수정
    @PutMapping("/member/{memberId}")
    public MemberDetailDto editMember(
            @PathVariable String memberId,
            @Valid @RequestBody EditUser.Request request

    ) {
        log.info("request : {}", request);
        return memberService.editMember(memberId, request);
    }


    // ctrl + alt + l 자동정렬
    // ctrl + alt + o 클래스 임포트 / 삭제
    @PostMapping("/create-member")
    public CreateMemberDTO.Response createMember(
            @Valid @RequestBody CreateMemberDTO.Request request
    ) {
        log.info("request : {}", request);
        return memberService.createMember(request);
    }


    @DeleteMapping("/member/{memberId}")
    public MemberDetailDto deleteMember(
            @PathVariable String memberId
    ) {
        log.info("request : {}", memberId);
        // Transaction
        // 1. EMPLOYER -> RETIRED
        // 2. SAVE INTO RetiredMember
        return memberService.deleteMember(memberId);
    }


    /**
     *  아래의 Exception은 Global Exception Handler로 통합함.
     *  lunchTimeException
     */
//    @ResponseStatus(value = HttpStatus.CONFLICT)
//    @ExceptionHandler
//    public MemberErrorResponse handleException (
//            MemberException e,
//            HttpServletRequest request
//    ){
//        log.error("errorCode {}, url: {}, message {}",
//                e.getUserErrorCode(), request.getRequestURI(), e.getDetailMessage());
//
//        return MemberErrorResponse.builder()
//                .errorCode(e.getUserErrorCode())
//                .errMessage(e.getDetailMessage())
//                .build();
//    }
}
