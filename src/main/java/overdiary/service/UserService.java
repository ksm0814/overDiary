package overdiary.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import overdiary.domain.User;
import overdiary.domain.UserRepository;
import overdiary.dto.UserDto;
import overdiary.exception.UserException;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Resource(name = "userRepository")
    UserRepository userRepository;

    public Iterable<User> findAllUser() {
        return userRepository.findAll();
    }

    User findByUserId(String userId){
        return userRepository.findByUserId(userId);
    }

    public User createUser(UserDto userDto) throws UserException {
        return userRepository.save(userDto.toUser());
    }

    public User login(String userId, String password) throws UserException {
       if(!Optional.ofNullable(findByUserId(userId)).orElseThrow(() -> new UserException("그런 사용자는 존재하지 않습니다")).isSamePassword(password))
           throw new UserException("비밀번호가 맞지 않습니다");
        return findByUserId(userId);
    }

    public User setGameInfo(long userKey) {
        User loginUser = userRepository.findOne(userKey);
        loginUser.setGameId("Link");
        loginUser.setGameBattletag("31107");
        return userRepository.save(loginUser);
    }

}
