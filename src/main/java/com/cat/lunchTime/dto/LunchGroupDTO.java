package com.cat.lunchTime.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    public static class LunchGroupDTOBuilder {
        private String name;
        private Integer age;
        private LocalDateTime startAt;

        LunchGroupDTOBuilder() {
        }

        public LunchGroupDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LunchGroupDTOBuilder age(Integer age) {
            this.age = age;
            return this;
        }

        public LunchGroupDTOBuilder startAt(LocalDateTime startAt) {
            this.startAt = startAt;
            return this;
        }

        public LunchGroupDTO build() {
            return new LunchGroupDTO(name, age, startAt);
        }

        public String toString() {
            return "LunchGroupDTO.LunchGroupDTOBuilder(name=" + this.name + ", age=" + this.age + ", startAt=" + this.startAt + ")";
        }
    }
}
