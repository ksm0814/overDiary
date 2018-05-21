package overdiary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import overdiary.domain.Article;
import overdiary.helper.HttpSessionUtils;
import overdiary.service.ArticleService;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class HomeController {
    // TODO : 한글 문제 해결 ㅠ 왜 다시 안되지..
    // TODO : TEST code 추가

    @Resource(name = "articleService")
    ArticleService articleService;

    @GetMapping("")
    public String welcome(Model model, HttpSession session) {
        Iterable<Article> articles = articleService.findAll();
        model.addAttribute("Articles", articles);
        model.addAttribute("Session", session.getAttribute(HttpSessionUtils.SESSION_KEY));
        return "/home/index";
    }
}