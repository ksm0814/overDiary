package com.overDiary.controller;

import com.overDiary.domain.Article;
import com.overDiary.helper.HttpSessionUtils;
import com.overDiary.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class HomeController {
    // 게시판은 두개로 나눠진다. 1) 자유게시판(공략팁같은거 적는 자유게시판), 2)명예의 전당(사진 전용 layout), 최고의 플레이 저장소(bootstrap inner)
    // 나의 프로필 1)플레이 영웅 원 color 차트, 2) 플레이 타임을 보여주는 막대차트

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
