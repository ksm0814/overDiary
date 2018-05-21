package com.overDiary.controller;


import com.overDiary.domain.User;
import com.overDiary.helper.LoginUser;
import com.overDiary.service.RankService;
import com.overDiary.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Resource(name = "userService")
    UserService userService;

    @Resource(name = "rankService")
    RankService rankService;

    @PostMapping("/rank/{userKey}")
    public User showMyRank(@LoginUser User loginUser, @PathVariable long userKey) throws Exception{
        return rankService.showMyRank(userService.setGameInfo(userKey));
    }
}
