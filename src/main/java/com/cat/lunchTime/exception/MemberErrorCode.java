package com.cat.lunchTime.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberErrorCode {
    DUPLICATED_MEMBER_ID("중복되는 아이디가 있습니다."),
    MEMBER_ID_LENGTH("아이디 길이는 8글자 이상입니다."),
    MEMBER_ID_NO("아이디가 없습니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다.");


    private final String message;

}
