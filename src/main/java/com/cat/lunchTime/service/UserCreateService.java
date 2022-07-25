package com.cat.lunchTime.service;

import com.cat.lunchTime.dto.CreateUserDTO;
import com.cat.lunchTime.dto.EditUser;
import com.cat.lunchTime.dto.UserDetailDto;
import com.cat.lunchTime.dto.UserDto;
import com.cat.lunchTime.entity.Member;
import com.cat.lunchTime.exception.MemberException;
import com.cat.lunchTime.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.cat.lunchTime.exception.MemberErrorCode.*;

@Service

@RequiredArgsConstructor
public class UserCreateService {

    // @Injection @Autowired 서비스 테스트가 어려웠다.
    // @RequiredArgsConstructor 자동으로 생성자를 만들어준다.
    private final UserRepository userRepository;
    private final EntityManager em;

    @Transactional
    public CreateUserDTO.Response createMember(CreateUserDTO.Request request){
            validateCreateUserRequest(request);


            // business logic start
            Member userInfo = Member.builder()
                    .userId(request.getUserId())
                    .userPw(request.getUserPw())
                    .name(request.getName())
                    .age(request.getAge())
                    .experienceYears(request.getExperienceYears())
                    .jobType(request.getJobType())
                    .foodCountry(request.getFoodCountry())
                    .build();
            userRepository.save(userInfo);
            return CreateUserDTO.Response.fromEntity(userInfo);
    }

    private void validateCreateUserRequest(CreateUserDTO.Request request) {
        // ctrl + alt + v 변수로 refactor 할 수 있다.
        userRepository.findByUserId(request.getUserId()).ifPresent((developer) -> {
            throw new MemberException(DUPLICATED_MEMBER_ID);
        });
    }


    public List<UserDto> getAllMembers() {
        return userRepository.findAll()
                .stream().map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }
    public UserDetailDto getMemberDetail(String memberId) {
        return userRepository.findByUserId(memberId)
                .map(UserDetailDto::fromEntity)
                .orElseThrow(() -> new MemberException(INVALID_REQUEST));
    }


    public UserDetailDto editMember(String memberId, EditUser.Request request) {
        validateEditeUserRequest(memberId, request);

        Member user = userRepository.findByUserId(memberId).orElseThrow(
                () -> new MemberException(MEMBER_ID_NO)
        );
        user.setJobType(request.getJobType());
        user.setFoodCountry(request.getFoodCountry());
        user.setAge(request.getAge());

        return UserDetailDto.fromEntity(user);
    }

    private void validateEditeUserRequest(String memberId, EditUser.Request request) {
        Member user = userRepository.findByUserId(memberId).orElseThrow(
                () -> new MemberException(MEMBER_ID_NO)
        );


    }


}
