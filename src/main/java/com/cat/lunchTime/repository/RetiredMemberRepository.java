package com.cat.lunchTime.repository;

import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.entity.RetiredMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetiredMemberRepository extends JpaRepository<RetiredMember, Long> {

}
