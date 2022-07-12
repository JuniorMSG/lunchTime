package com.cat.lunchTime.service;

import com.cat.lunchTime.dto.CreateUserDTO;
import com.cat.lunchTime.entity.UserInfo;
import com.cat.lunchTime.repository.UserRepository;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Service

@RequiredArgsConstructor
public class UserCreateService {

    // @Injection @Autowired 서비스 테스트가 어려웠다.
    // @RequiredArgsConstructor 자동으로 생성자를 만들어준다.
    private final UserRepository userRepository;
    private final EntityManager em;

    @Transactional
    public void createUser(CreateUserDTO.Request request){

            validateCreateUserRequest(request);
            // business logic start
            UserInfo userInfo = UserInfo.builder()
                    .userId("milk1234")
                    .userPw("1234")
                    .name("MS")
                    .age(29)
                    .jobType(JobType.Developer)
                    .foodCountry(FoodCountry.Korea)
                    .build();


            userRepository.save(userInfo);
    }

    private void validateCreateUserRequest(CreateUserDTO.Request request) {
    }
}
