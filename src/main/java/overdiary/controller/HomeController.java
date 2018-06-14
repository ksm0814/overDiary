package overdiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import overdiary.domain.Article;
import overdiary.domain.User;
import overdiary.helper.HttpSessionUtils;
import overdiary.helper.LoginUser;
import overdiary.service.AlarmService;
import overdiary.service.ArticleService;
import overdiary.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class HomeController {

    @Resource(name = "articleService")
    ArticleService articleService;

    @Resource(name = "alarmService")
    AlarmService alarmService;

    @Resource(name = "userService")
    UserService userService;

    @GetMapping("")
    public String welcome(Model model, HttpSession session) {
        Iterable<Article> articles = articleService.findAll();
        model.addAttribute("Articles", articles);
        model.addAttribute("Session", session.getAttribute(HttpSessionUtils.SESSION_KEY));
        User loginUser = null;
        for (User user :
                userService.findAllUser()) {
            loginUser = user;
        }
        model.addAttribute("Alarms", alarmService.findNotOpened(loginUser));
        return "/home/index";
    }
}
