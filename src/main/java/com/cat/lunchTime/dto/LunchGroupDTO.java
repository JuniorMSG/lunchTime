package com.cat.lunchTime.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


/**
 * @author MSG
 * @since 1.0
 */
// lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
// @UtilityClass
// @ToString 필요할때만
// @RequiredArgsConstructor
// @Data
public class LunchGroupDTO {
    // shift + F6 동시 수정
    String name;
    Integer age;
    LocalDateTime startAt;

    public static LunchGroupDTOBuilder builder() {
        return new LunchGroupDTOBuilder();
    }

    /*
     *  Ctrl + Shift + T : 테스트 파일 만들기 혹은 이동하기
     */

    public void printLog(){
        log.info("==LOG==");
    }

}
