package com.cat.lunchTime.controller;

import com.cat.lunchTime.dto.CreateUserDTO;
import com.cat.lunchTime.dto.EditUser;
import com.cat.lunchTime.dto.UserDto;
import com.cat.lunchTime.dto.UserDetailDto;
import com.cat.lunchTime.service.UserCreateService;
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
    private final UserCreateService userCreateService;

    @GetMapping("/members")
    public List<UserDto> getAllMembers() {
        log.info("Get /getGroupInfo HTTP/1.1 ");
        return userCreateService.getAllMembers();
    }

    @GetMapping("/member/{memberId}")
    public UserDetailDto getMemberDetail(
            @PathVariable String memberId
    ) {
        // 중괄호로 넣게되면 pass variable으로 값을 가져 올 수 있게된다.
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return userCreateService.getMemberDetail(memberId);
    }

    // Put 데이터 전체 수정
    // Fetch 데이터 일부 수정
    @PutMapping("/member/{memberId}")
    public UserDetailDto editMember(
            @PathVariable String memberId,
            @Valid @RequestBody EditUser.Request request

    ) {
        log.info("request : {}", request);
        return userCreateService.editMember(memberId, request);
    }


    // ctrl + alt + l 자동정렬
    // ctrl + alt + o 클래스 임포트 / 삭제
    @PostMapping("/create-member")
    public CreateUserDTO.Response createMember(
            @Valid @RequestBody CreateUserDTO.Request request
    ) {
        log.info("request : {}", request);
        return userCreateService.createMember(request);
    }



}
