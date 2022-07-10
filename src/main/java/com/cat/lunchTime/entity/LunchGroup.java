package com.cat.lunchTime.entity;

import com.cat.lunchTime.type.GroupMember;
import com.cat.lunchTime.type.MemberFood;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
public class LunchGroup
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    private GroupMember groupMember;

    @Enumerated(EnumType.STRING)
    private MemberFood memberFood;

    private Integer price;
    private String GroupName;
    private String GroupId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updateAt;
}
