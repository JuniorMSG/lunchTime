package com.cat.lunchTime.exception;

import lombok.Getter;

/**
 * @author WorkDevCat
 */
@Getter
public class MemberException extends RuntimeException {
    private MemberErrorCode memberErrorCode;
    private String detailMessage;

    // 오버라이딩
    public MemberException(MemberErrorCode errorCode){
        super(errorCode.getMessage());
        this.memberErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();;
    }
    public MemberException(MemberErrorCode errorCode, String detailMessage){
        super(detailMessage);
        this.memberErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
