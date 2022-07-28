package com.cat.lunchTime.entity;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.type.MemberLevel;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.cat.lunchTime.entity
 * fileName       : LunchGroup
 * author         : Cat
 * date           : 2022-07-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-07-11        Cat       최초 생성
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
// Entity에서 특정 DTO 하나만을 위한 메서드는 좋지 않다.
@EntityListeners(AuditingEntityListener.class)
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String memberId;
    private String password;
    private String name;
    private Integer age;
    private Integer experienceYears;


    @Enumerated(EnumType.STRING)
    private MemberLevel memberLevel;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private FoodCountry foodCountry;

    @Enumerated(EnumType.STRING)
    private StatusCode statusCode;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;
}
