package com.overDiary.controller;

import com.overDiary.dto.UserDto;
import com.overDiary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    UserService userService;

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @PostMapping("")
    public String create(String userId, String name, String password, String email){
        log.info("Saved User : {}", userService.createUser(new UserDto(userId, name, password, email)));
        return "redirect:/";
    }
}
