package com.cat.lunchTime.service;

import com.cat.lunchTime.entity.LunchGroup;
import com.cat.lunchTime.repository.LunchTimeRepository;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.GroupMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LunchTimeService {

    // @Injection @Autowired 서비스 테스트가 어려웠다.
    // @RequiredArgsConstructor 자동으로 생성자를 만들어준다.
    private final LunchTimeRepository lunchTimeRepository;

    @Transactional
    public void createLunchGroup(){
    }
}
