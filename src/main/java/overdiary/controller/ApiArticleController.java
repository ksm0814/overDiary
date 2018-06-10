package overdiary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import overdiary.domain.Article;
import overdiary.service.ArticleService;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/articles")
public class ApiArticleController{
    private static final Logger log = LoggerFactory.getLogger(ApiArticleController.class);

    @Resource(name = "articleService")
    ArticleService articleService;

    @GetMapping("/{articleKey}")
    public Article show(@PathVariable long articleKey) {
        return articleService.findById(articleKey);
    }

    @GetMapping("/updateAlarm")
    public List<Article> showRecent(){
        return articleService.getUpdate();
    }

}
