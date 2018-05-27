package overdiary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import overdiary.domain.Article;
import overdiary.domain.ArticleRepository;
import overdiary.domain.Attachment;
import overdiary.domain.User;
import overdiary.dto.ArticleDto;

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

    public Article getUpdate() {

//        Iterable<Article> recent = articleRepository.findAll();
//        List<Article> articles = new ArrayList<>();
//        int maxCount = 5;
//        while(recent.iterator().hasNext() || maxCount != 0){
//            articles.add(recent.iterator().next());
//            maxCount -= 1;
//        }
        return articleRepository.findByTitle("test title1").get(0);

    }
}
