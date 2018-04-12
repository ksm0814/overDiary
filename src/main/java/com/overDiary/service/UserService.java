package com.overDiary.service;


import com.overDiary.domain.User;
import com.overDiary.domain.UserRepository;
import com.overDiary.dto.UserDto;
import com.overDiary.exception.UserException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User createUser(UserDto userDto) throws UserException {
        Optional.ofNullable(findByUserId(userDto.getUserId())).orElseThrow(() -> new UserException("이미 존재하는 ID 입니다."));
        return userRepository.save(userDto.toUser());
    }
}
