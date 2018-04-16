package com.overDiary.service;

import com.overDiary.domain.Article;
import com.overDiary.domain.ArticleRepository;
import com.overDiary.dto.ArticleDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {

    @Resource(name = "articleRepository")
    ArticleRepository articleRepository;

    public Iterable<Article> findAll(){
        return articleRepository.findAll();
    }

    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public void create(ArticleDto articleDto) {
        articleRepository.save(articleDto.toArticle());
    }
}
