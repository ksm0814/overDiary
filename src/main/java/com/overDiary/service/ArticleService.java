package com.overDiary.service;

import com.overDiary.domain.Article;
import com.overDiary.domain.ArticleRepository;
import com.overDiary.domain.Attachment;
import com.overDiary.domain.User;
import com.overDiary.dto.ArticleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {
    private static final Logger log = LoggerFactory.getLogger(ArticleService.class);

    @Resource(name = "articleRepository")
    ArticleRepository articleRepository;

    public Iterable<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public Article findById(long articleKey) {
        return articleRepository.findOne(articleKey);
    }

    public List<Article> findByLabel(String label) {
        return articleRepository.findByLabel(label);
    }


    public void create(User loginUser, ArticleDto articleDto, Attachment attachment) {
        Article article = articleDto.toArticle();
        article.setWriter(loginUser);
        article.addFile(attachment);
        Article newArticle = articleRepository.save(article);
        log.info("삽입할 글 정보 : {}", newArticle.getTitle());
    }


    @Transactional
    public Article viewArticle(long articleKey) {
        Article article = findById(articleKey);
        article.setViews();
        return article;
    }
}
