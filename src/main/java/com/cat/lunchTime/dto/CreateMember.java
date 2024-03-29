package com.cat.lunchTime.dto;

import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import com.cat.lunchTime.type.MemberLevel;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 */
@Slf4j
public class CreateMember {

   @Getter
   @Setter
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   public static class Request{

      @NotNull
      private String memberId;

      @NotNull
      @Size(min = 3, max = 25, message = "pw size must 3~25")
      private String password;

      @NotNull
      @Size(min = 3, max = 50, message = "name size must 3~50")
      private String name;

      @NotNull
      @Min(15)
      private Integer age;

      @NotNull
      private Integer experienceYears;

      @NotNull
      private MemberLevel memberLevel;

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
      private String memberId;
      private String password;
      private String name;
      private Integer age;
      private Integer experienceYears;
      private MemberLevel memberLevel;
      private JobType jobType;
      private FoodCountry foodCountry;

      public static Response fromEntity(Member member){
         return Response.builder()
                 .memberId(member.getMemberId())
                 .password(member.getPassword())
                 .name(member.getName())
                 .age(member.getAge())
                 .jobType(member.getJobType())
                 .experienceYears(member.getExperienceYears())
                 .foodCountry(member.getFoodCountry())
                 .memberLevel(member.getMemberLevel())
                 .build();
      }
   }
}
