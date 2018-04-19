package com.overDiary.controller;

import com.overDiary.domain.Article;
import com.overDiary.domain.User;
import com.overDiary.dto.ArticleDto;
import com.overDiary.helper.LoginUser;
import com.overDiary.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Resource(name = "articleService")
    ArticleService articleService;

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
        Article article = articleService.createBlank();
        model.addAttribute("ArticleKey", article.getArticleKey());
        return "/article/form";
    }

    @PostMapping("/create/{articleKey}")
    public String create(@LoginUser User loginUser, String label, String title, String contents, String openRange, String fileName, @PathVariable long articleKey) {
        log.info("fileName : {}", fileName);
        ArticleDto articleDto = new ArticleDto(title, contents, label, Boolean.parseBoolean(openRange));
        articleService.create(loginUser,articleKey, articleDto);
        return "redirect:/articles";
    }
}
