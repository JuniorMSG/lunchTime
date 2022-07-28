package com.cat.lunchTime.dto;

import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 */
@Slf4j
public class EditMember {

   @Getter
   @Setter
   @AllArgsConstructor
   @NoArgsConstructor
   @Builder
   public static class Request{


      @NotNull
      @Size(min = 3, max = 25, message = "pw size must 3~25")
      private String userPw;

      @NotNull
      private JobType jobType;
      @NotNull
      private FoodCountry foodCountry;


      @NotNull
      private Integer experienceYears;

      @NotNull
      private Integer age;

   }

}
