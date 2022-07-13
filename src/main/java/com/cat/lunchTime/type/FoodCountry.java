package com.cat.lunchTime.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.cat.lunchTime.entity
 * fileName       : MemberFood
 * author         : Cat
 * date           : 2022-07-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-11        Cat       최초 생성
 */
@AllArgsConstructor
@Getter
public enum FoodCountry {
    Korea("한식"),
    Japan("일식"),
    China("중식"),
    USA("양식")
    ;
    
    private final String description;
    
}
