package com.cat.lunchTime.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.cat.lunchTime.entity
 * fileName       : GroupMember
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
public enum GroupMember {
    NEW("신입"),
    JUNIOR("주니어"),
    MIDDLE("중간"),
    SENIOR("시니어"),
    TEAMLEADER("팀장")
    ;

    private final String description;

}
