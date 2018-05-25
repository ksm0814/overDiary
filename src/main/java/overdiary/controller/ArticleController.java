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
import overdiary.service.ArticleService;
import overdiary.service.AttachmentService;

import javax.annotation.Resource;
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
    public String show(@PathVariable long articleKey, Model model) {
        model.addAttribute("Article", articleService.viewArticle(articleKey));
        return "/article/show";
    }

    @GetMapping("/form")
    public String form(Model model) {

        return "/article/form";
    }


    @PostMapping("/create")
    public String create(@LoginUser User loginUser, String label, String title, String contents, String openRange, Long hiddenPath) {
        log.info("filePath : {}", attachmentService.findOne(hiddenPath).getFilePath());
        ArticleDto articleDto = new ArticleDto(title, contents, label, Boolean.parseBoolean(openRange));
        articleService.create(loginUser, articleDto, attachmentService.findOne(hiddenPath));
        return "redirect:/articles";
    }
}
