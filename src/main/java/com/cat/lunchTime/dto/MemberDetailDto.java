package com.cat.lunchTime.dto;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
public class MemberDetailDto {


    private String userId;
    private String userPw;
    private String name;
    private Integer age;
    private Integer experienceYears;
    private JobType jobType;
    private FoodCountry foodCountry;
    private StatusCode statusCode;

    public static MemberDetailDto fromEntity(Member userInfo) {
        return MemberDetailDto.builder()
                .userId(userInfo.getUserId())
                .userPw(userInfo.getUserPw())
                .name(userInfo.getName())
                .age(userInfo.getAge())
                .experienceYears(userInfo.getExperienceYears())
                .jobType(userInfo.getJobType())
                .foodCountry(userInfo.getFoodCountry())
                .statusCode(userInfo.getStatusCode())
                .build();
    }

}
