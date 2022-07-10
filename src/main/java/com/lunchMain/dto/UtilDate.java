package com.lunchMain.dto;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass

public class UtilDate {
    /**
     * 유틸리티 클래스에 적용하는 어노테이션.
     * @UtilityClass 은 기본생성자가 private 으로 생성되며 만약 reflection 혹은 내부에서 생성자를 호출할 경우에는
     * UnsupportedOperationException 이 발생한다.
     * 날짜변환 숫자변환등 공통으로 쓸 수 있는 유틸 클래스 들을 다룰때 사용한다.
     * Util 클래스는 더이상 상속을 받으면 안된다.
     */
    public static void printLog(){
        System.out.println("Util Date는 생성자 생성이 안된다");
        System.out.println(LocalDateTime.of(2021,7,20,10,10));
    }
}
