package com.cat.lunchTime.exception;

import lombok.Getter;

/**
 * @author WorkDevCat
 */
@Getter
public class UserException extends RuntimeException {
    private UserErrorCode userErrorCode;
    private String detailMessage;

    // 오버라이딩
    public UserException(UserErrorCode errorCode){
        super(errorCode.getMessage());
        this.userErrorCode = errorCode;
        this.detailMessage = errorCode.getMessage();;
    }
    public UserException(UserErrorCode errorCode, String detailMessage){
        super(detailMessage);
        this.userErrorCode = errorCode;
        this.detailMessage = detailMessage;
    }
}
