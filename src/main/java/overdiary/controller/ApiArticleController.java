package overdiary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import overdiary.domain.Article;
import overdiary.service.ArticleService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/articles")
public class ApiArticleController {

    @Resource(name = "articleService")
    ArticleService articleService;

    @GetMapping("/{articleKey}")
    public Article show(@PathVariable long articleKey) {
        return articleService.findById(articleKey);
    }

}
