package com.cat.lunchTime.dto;

import com.cat.lunchTime.exception.MemberErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberErrorResponse {

    private MemberErrorCode errorCode;
    private String errorMessage;
}

