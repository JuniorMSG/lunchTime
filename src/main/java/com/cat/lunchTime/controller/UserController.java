package com.cat.lunchTime.controller;

import com.cat.lunchTime.dto.CreateUserDTO;
import com.cat.lunchTime.dto.UserInfoDto;
import com.cat.lunchTime.dto.UserInfoDetailDto;
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
public class UserController {
    private final UserCreateService userCreateService;

    @GetMapping("/getGroupInfos")
    public List<UserInfoDto> getGroupbInpos() {
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return userCreateService.getAllIds();
    }

    @GetMapping("/getGroupInfos/{memberId}")
    public UserInfoDetailDto getGroupbInpoDetail(
            @PathVariable String memberId
    ) {
        // 중괄호로 넣게되면 pass variable으로 값을 가져 올 수 있게된다.
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return userCreateService.getGroupbInpoDetail(memberId);
    }

    // ctrl + alt + l 자동정렬
    // ctrl + alt + o 클래스 임포트 / 삭제
    @PostMapping("/create-user")
    public CreateUserDTO.Response createUser(
            @Valid @RequestBody CreateUserDTO.Request request
    ) {
        log.info("request : {}", request);
        return userCreateService.createUser(request);
    }
}
