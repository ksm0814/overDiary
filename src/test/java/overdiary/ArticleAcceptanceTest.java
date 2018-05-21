package overdiary;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import overdiary.domain.ArticleRepository;
import overdiary.support.AcceptanceTest;
import overdiary.support.ResponseBuilder;

import javax.annotation.Resource;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ArticleAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ArticleAcceptanceTest.class);

    @Resource(name = "articleRepository")
    ArticleRepository articleRepository;

    @Test
    public void getArticleForm() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/articles/form", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.info("글작성 HTML BODY : {}", response.getBody());
    }

    public void createTestFile(){
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder
                .multipartFormData()
                .addParameter("file", new ClassPathResource("import.sql"))
                .build();
        template().postForEntity("/api/attachments", request, String.class);
    }

    public ResponseEntity<String> createTestArticle(String title, String contents, String label, long attachmentKey){
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder.urlEncodedForm().
                addParameter("title", title).addParameter("label", label).addParameter("contents", contents).addParameter("openRange", "true").addParameter("hiddenPath", attachmentKey).build();

        return basicAuthTemplate().postForEntity("/articles/create", request, String.class);
    }

    public Long findArticleKey(String title) {
        return articleRepository.findByTitle(title).get(0).getArticleKey();
    }

    @Test
    public void createArticle() throws Exception{
        createTestFile();
        ResponseEntity<String> response = createTestArticle("create 제목", "create 내용", "tip", 1L);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));

        ResponseEntity<String> response2 = template.getForEntity("/articles", String.class);
        log.info("article liist HTML BODY {}",response2.getBody());
        assertTrue(response2.getBody().contains("create 제목"));

    }

    @Test
    public void showArticle() throws Exception{
        createTestFile();
        createTestArticle("show 제목", "show 내용", "tip", 1L);

        ResponseEntity<String> response2 = template.getForEntity(String.format("/articles/%d", findArticleKey("show 제목")), String.class);
        log.info("article liist HTML BODY {}",response2.getBody());
        assertTrue(response2.getBody().contains("show 제목"));
        assertTrue(response2.getBody().contains("show 내용"));
    }
}
