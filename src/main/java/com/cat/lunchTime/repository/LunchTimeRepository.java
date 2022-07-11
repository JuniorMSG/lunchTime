package com.cat.lunchTime.repository;

import com.cat.lunchTime.entity.LunchGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LunchTimeRepository extends JpaRepository<LunchGroup, Long> {

}
