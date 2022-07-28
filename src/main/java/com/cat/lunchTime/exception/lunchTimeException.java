package com.cat.lunchTime.exception;

import com.cat.lunchTime.dto.MemberErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static com.cat.lunchTime.exception.MemberErrorCode.INTERNAL_SERVER_ERROR;
import static com.cat.lunchTime.exception.MemberErrorCode.INVALID_REQUEST;

// 각 컨트롤에 어드바이스를 해주는 어노테이션
@Slf4j
@RestControllerAdvice
public class lunchTimeException {

    // ExceptionHandler를 쓰는 이유
    // 1. 편리하게 Exception을 핸들링 할 수 있다.
    // 2. 정의되지 않은 값(너무 Low한 레벨의 에러) 으로 에러가 나갈경우 어떤 문제가 생길지 명확하지 않다.
    // 3. 불필요하게 너무 자세한 에러를 내려 줄 경우 여러가지 위협을 당할 수 있게된다. (SQL Injection 등)
    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public MemberErrorResponse handleDMakerException(
            MemberException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}", e.getUserErrorCode(),
                request.getRequestURI(), e.getDetailMessage(), e);
        return MemberErrorResponse.builder()
                .errorCode(e.getUserErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public MemberErrorResponse handleBadRequest (
            MemberException e, HttpServletRequest request
    ){
        log.error("url: {}, message {}", request.getRequestURI(), e.getDetailMessage());

        return MemberErrorResponse.builder()
                .errorCode(INVALID_REQUEST)
                .errorMessage(INVALID_REQUEST.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MemberErrorResponse handleException (
            MemberException e, HttpServletRequest request
    ){
        log.error("url: {}, message {}", request.getRequestURI(), e.getDetailMessage());
        return MemberErrorResponse.builder()
                .errorCode(INTERNAL_SERVER_ERROR)
                .errorMessage(INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }


}
