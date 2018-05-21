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

    // 내일 할거
    // 이름#배틀태그   방식으로 검색해서 정보 띄워주기 (모스트픽, 승률 정도??)
    // 정보 활용해서 차트 쓰는법 고안
    // 알람에 오버워치 소식을 간단하게 띄우고 navbar에 소식통을 넣어서 자세히 볼숭있게
    // comment 기능 추가

    @Resource(name = "articleService")
    ArticleService articleService;

    @Resource(name = "attachmentService")
    AttachmentService attachmentService;

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
