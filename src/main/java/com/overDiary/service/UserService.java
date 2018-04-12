package com.overDiary.service;


import com.overDiary.domain.User;
import com.overDiary.domain.UserRepository;
import com.overDiary.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource(name = "userRepository")
    UserRepository userRepository;

    Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    List<User> findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User createUser(UserDto userDto) {
        return userRepository.save(userDto.toUser());
    }
}
