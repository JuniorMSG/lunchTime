package com.cat.lunchTime.dto;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import com.cat.lunchTime.type.MemberLevel;
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
    private MemberLevel memberLevel;
    private StatusCode statusCode;

    public static MemberDetailDto fromEntity(Member member) {
        return MemberDetailDto.builder()
                .userId(member.getMemberId())
                .userPw(member.getPassword())
                .name(member.getName())
                .age(member.getAge())
                .experienceYears(member.getExperienceYears())
                .jobType(member.getJobType())
                .foodCountry(member.getFoodCountry())
                .memberLevel(member.getMemberLevel())
                .statusCode(member.getStatusCode())
                .build();
    }

}
