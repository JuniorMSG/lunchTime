package com.cat.lunchTime.entity;

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
@EntityListeners(AuditingEntityListener.class)
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String userId;
    private String userPw;
    private String name;
    private Integer age;
    private Integer experienceYears;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private FoodCountry foodCountry;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;
}
