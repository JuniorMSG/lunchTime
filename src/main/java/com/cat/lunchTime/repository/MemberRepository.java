package com.cat.lunchTime.repository;

import com.cat.lunchTime.code.StatusCode;
import com.cat.lunchTime.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.NotAcceptableStatusException;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

    List<Member> findMemberByStatusCodeEquals(StatusCode statusCode);

}
