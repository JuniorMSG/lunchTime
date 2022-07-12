package com.cat.lunchTime.service;

import com.cat.lunchTime.dto.CreateUserDTO;
import com.cat.lunchTime.entity.UserInfo;
import com.cat.lunchTime.exception.UserErrorCode;
import com.cat.lunchTime.exception.UserException;
import com.cat.lunchTime.repository.UserRepository;
import com.cat.lunchTime.type.FoodCountry;
import com.cat.lunchTime.type.JobType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.Optional;

import static com.cat.lunchTime.exception.UserErrorCode.DUPLICATED_MEMBER_ID;
import static com.cat.lunchTime.exception.UserErrorCode.MEMBER_ID_LENGTH;

@Service

@RequiredArgsConstructor
public class UserCreateService {

    // @Injection @Autowired 서비스 테스트가 어려웠다.
    // @RequiredArgsConstructor 자동으로 생성자를 만들어준다.
    private final UserRepository userRepository;
    private final EntityManager em;

    @Transactional
    public CreateUserDTO.Response createUser(CreateUserDTO.Request request){

            validateCreateUserRequest(request);
            // business logic start
            UserInfo userInfo = UserInfo.builder()
                    .userId(request.getUserId())
                    .userPw(request.getUserPw())
                    .name(request.getName())
                    .age(request.getAge())
                    .jobType(request.getJobType())
                    .foodCountry(request.getFoodCountry())
                    .build();

            userRepository.save(userInfo);
            return CreateUserDTO.Response.fromEntity(userInfo);
    }

    private void validateCreateUserRequest(CreateUserDTO.Request request) {

        // ctrl + alt + v 변수로 refactor 할 수 있다.
        String userId = request.getUserId();
        if(userId.length() < 8){
            throw new UserException(DUPLICATED_MEMBER_ID);
        }


//        Optional<UserInfo> userInfo = userRepository.findByUserId(request.getUserId());
//        if (userInfo.isPresent())
//            throw new UserException(MEMBER_ID_LENGTH);
        userRepository.findByUserId(request.getUserId()).ifPresent((userInfo -> {
            throw new UserException(MEMBER_ID_LENGTH);
        }));


    }
}
