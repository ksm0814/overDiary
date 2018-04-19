package com.overDiary.service;

import com.overDiary.domain.Article;
import com.overDiary.domain.ArticleRepository;
import com.overDiary.domain.Attachment;
import com.overDiary.domain.User;
import com.overDiary.dto.ArticleDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ArticleService {

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


    @Transactional
    public void create(User loginUser, long articleKey, ArticleDto articleDto) {
        Article article = findById(articleKey);
        article.setDto(articleDto);
        article.setWriter(loginUser);
    }

    public Article createBlank() {
        return articleRepository.save(new Article());
    }

    @Transactional
    public void setAttachment(long articleKey, Attachment attachment) {
        Article article = findById(articleKey);
        article.addFile(attachment);

    }

    @Transactional
    public Article viewArticle(long articleKey) {
        Article article = findById(articleKey);
        article.setViews();
        return article;
    }
}
