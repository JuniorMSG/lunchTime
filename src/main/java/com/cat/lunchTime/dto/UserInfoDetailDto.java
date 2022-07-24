package com.cat.lunchTime.dto;

import com.cat.lunchTime.entity.UserInfo;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Builder
public class UserInfoDetailDto {


    private String userId;
    private String userPw;
    private String name;
    private Integer age;
    private JobType jobType;
    private FoodCountry foodCountry;

    public static UserInfoDetailDto fromEntity(UserInfo userInfo) {
        return UserInfoDetailDto.builder()
                .userId(userInfo.getUserId())
                .userPw(userInfo.getUserPw())
                .jobType(userInfo.getJobType())
                .foodCountry(userInfo.getFoodCountry())
                .name(userInfo.getName())
                .build();
    }

}
