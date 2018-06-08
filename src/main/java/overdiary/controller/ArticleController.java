package overdiary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import overdiary.domain.Article;
import overdiary.domain.User;
import overdiary.dto.ArticleDto;
import overdiary.helper.LoginUser;
import overdiary.service.AlarmService;
import overdiary.service.ArticleService;
import overdiary.service.AttachmentService;
import overdiary.service.UserService;

import javax.annotation.Resource;
import javax.validation.ReportAsSingleViolation;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Resource(name = "articleService")
    private ArticleService articleService;

    @Resource(name = "attachmentService")
    private AttachmentService attachmentService;

    @Resource(name = "alarmService")
    private AlarmService alarmService;

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("")
    public String articleList(Model model) {
        List<Article> articles = new ArrayList<>();
        for (Article article : articleService.findAll()) {
            if(article.isOpenRange())
                articles.add(article);
        }
        model.addAttribute("Articles", articles);
        return "/article/list";
    }

    @GetMapping("/{articleKey}")
    public String show(@LoginUser User loginUser, @PathVariable long articleKey, Model model) {
        model.addAttribute("Article", articleService.viewArticle(articleKey, loginUser));
        return "/article/show";
    }

    @GetMapping("/alarm/{articleKey}/{alarmKey}")
    public String alarmClick(@LoginUser User loginUser, @PathVariable long articleKey, @PathVariable long alarmKey) {
        log.info("alarm clicked!!: {}. {}", articleKey, alarmKey);
        alarmService.removeAlarm(alarmKey);
        return String.format("redirect:/articles/%d", articleKey);
    }

    @GetMapping("/form")
    public String form(Model model) {

        return "/article/form";
    }


    @PostMapping("/create")
    public String create(@LoginUser User loginUser, String label, String title, String contents, String openRange, Long hiddenPath) {
        log.info("filePath : {}", attachmentService.findOne(hiddenPath).getFilePath());
        ArticleDto articleDto = new ArticleDto(title, contents, label, Boolean.parseBoolean(openRange));
        alarmService.create(articleService.create(loginUser, articleDto, attachmentService.findOne(hiddenPath)), userService.findAllUser());
        return "redirect:/articles";
    }
}
