package com.cat.lunchTime.dto;

import com.cat.lunchTime.entity.UserInfo;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 */
@Slf4j
public class CreateUserDTO {

   @Getter
   @Setter
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   public static class Request{

      @NotNull
      private String userId;

      @NotNull
      @Size(min = 3, max = 25, message = "pw size must 3~25")
      private String userPw;

      @NotNull
      @Size(min = 3, max = 50, message = "name size must 3~50")
      private String name;

      @NotNull
      @Min(15)
      private Integer age;

      @NotNull
      private JobType jobType;

      @NotNull
      private FoodCountry foodCountry;
   }

   @Getter
   @Setter
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   public static class Response{
      private String userId;
      private String userPw;
      private String name;
      private Integer age;
      private JobType jobType;
      private FoodCountry foodCountry;

      public static Response fromEntity(UserInfo userInfo){
         return Response.builder()
                 .userId(userInfo.getUserId())
                 .userPw(userInfo.getUserPw())
                 .name(userInfo.getName())
                 .age(userInfo.getAge())
                 .jobType(userInfo.getJobType())
                 .foodCountry(userInfo.getFoodCountry())
                 .build();
      }
   }
}
