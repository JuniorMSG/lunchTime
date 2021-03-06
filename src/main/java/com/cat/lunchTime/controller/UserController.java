package com.cat.lunchTime.controller;

import com.cat.lunchTime.dto.CreateUserDTO;
import com.cat.lunchTime.service.UserCreateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
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

    @GetMapping("/getGroupInfo")
    public List<String> getGroupbInpo() {
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return Arrays.asList("직장", "집", "학원");
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
