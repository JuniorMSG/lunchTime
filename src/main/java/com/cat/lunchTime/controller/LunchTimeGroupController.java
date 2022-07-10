package com.cat.lunchTime.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class LunchTimeGroupController {

    @GetMapping("/getGroupInfo")
    public List<String> getGroupbInpo(){
        log.info("Get /getGroupInfo HTTP/1.1 ");

        return Arrays.asList("직장", "집", "학원");
    }
}
