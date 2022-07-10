package com.cat.lunchTime.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class LunchGroupDTOTest {
    @Test
    void test() {

        LunchGroupDTO lunchDTO = LunchGroupDTO.builder()
                .name("Name")
                .age(2988)
                .startAt(LocalDateTime.now())
                .build();

        System.out.println("==== Builder Test ====");
        System.out.println(lunchDTO.getName());
        System.out.println(lunchDTO.getAge());
        System.out.println(lunchDTO.getStartAt());
        System.out.println("==== Builder Test ====");

    }




}