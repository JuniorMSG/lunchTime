package com.cat.lunchTime.dto;

import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
@ToString
public class MemberDto {
    // entity 와 dto를 분리하는게 유연성이 더 좋다.

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private FoodCountry foodCountry;

    private String name;

    public static MemberDto fromEntity(Member member) {
        return MemberDto.builder()
                .jobType(member.getJobType())
                .foodCountry(member.getFoodCountry())
                .name(member.getName())
                .build();
    }
}
