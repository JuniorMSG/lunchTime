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
public enum MemberFood {
    Korea("경양식 돈까스"),
    Japan("일본식 돈까스"),
    china("짜장면"),
    USA("스테이크")
    ;

    private final String description;
    
}
