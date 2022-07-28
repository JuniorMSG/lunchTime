package com.cat.lunchTime.service;

import com.cat.lunchTime.dto.CreateMember;
import com.cat.lunchTime.dto.MemberDto;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

// Bean등을 다 생성해준다.
@SpringBootTest
class MemberServiceTestMockito {

    // @SpringBootTest 입력시 모든 Bean을 사용 할 수 있다.
    @Autowired
    private MemberService memberService;

    @Test
    public void testSomething(){
        // 아래 처럼 작성하면 DB랑 연결이 불가능하서 결국 격리성이 떨어진다.
        memberService.createMember(CreateMember.Request.builder()
                .foodCountry(FoodCountry.Korea)
                .jobType(JobType.Developer)
                .experienceYears(12)
                .memberId("milk9999")
                .password("12345678")
                .age(32)
                .name("DevCat")
                .build()
        );
        List<MemberDto> allEmployedMembers = memberService.getAllEmployedMembers();
        System.out.println("========================");
        System.out.println(allEmployedMembers);
        System.out.println("========================");
//        String result = "hello" + "world!";
//        assertEquals("hello world", result);
    }

}