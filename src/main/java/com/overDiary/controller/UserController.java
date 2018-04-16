package com.overDiary.controller;

import com.overDiary.dto.UserDto;
import com.overDiary.exception.UserException;
import com.overDiary.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
    public String create(String userId, String name, String password, String email) throws Exception {
        log.info("Saved User : {}", userService.createUser(new UserDto(userId, name, password, email)));
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session, Model model) {
        try {
            userService.login(userId, password);
        } catch (UserException e) {
            log.info("USER EXCEPTION ERROR : {}", e.getMessage());
            return "redirect:/user/loginForm";
        }
        session.setAttribute("SESSION_KEY", userId);
        model.addAttribute("SESSION", session);
        return "redirect:/";
    }
}
