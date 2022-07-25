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
public enum JobType {

    PM("프로젝트 매니저"),
    PL("프로젝트 리더"),
    TeamLeader("팀장"),
    FrontDeveloper("프론트 개발자"),
    ServerDeveloper("서버 개발자"),
    Developer("개발자"),
    Designer("디자이너")
    ;

    private final String description;
}
