package overdiary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import overdiary.domain.User;
import overdiary.dto.UserDto;
import overdiary.exception.UserException;
import overdiary.service.AlarmService;
import overdiary.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource(name = "userService")
    UserService userService;

    @Resource(name = "alarmService")
    AlarmService alarmService;

    @GetMapping("/form")
    public String form() {
        return "/user/form";
    }

    @PostMapping("")
    public String create(String userId, String name, String password, String email) throws Exception {
        return "redirect:/";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @PostMapping("/login")
    public String login(String userId, String password, HttpSession session, Model model) {
        User user;
        try {
            user = userService.login(userId, password);
        } catch (UserException e) {
            log.info("USER EXCEPTION ERROR : {}", e.getMessage());
            return "redirect:/user/loginForm";
        }

        session.setAttribute("SESSION_KEY",user);
        model.addAttribute("SESSION", session);
        return "redirect:/";
    }
}
