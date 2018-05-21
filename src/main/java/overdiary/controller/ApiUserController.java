package overdiary.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import overdiary.domain.User;
import overdiary.service.RankService;
import overdiary.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    UserService userService;

    @Resource(name = "rankService")
    RankService rankService;

    @GetMapping("/rank/{userKey}")
    public User showMyRank(@PathVariable long userKey) throws Exception{
        return rankService.showMyRank(userService.setGameInfo(userKey));
    }
}
