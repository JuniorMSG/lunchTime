package com.cat.lunchTime.repository;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    List<Member> findMemberByStatusCodeEquals(StatusCode statusCode);

}
