package overdiary;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import overdiary.support.AcceptanceTest;
import overdiary.support.ResponseBuilder;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class UserAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(UserAcceptanceTest.class);

    @Test
    public void getCreateForm() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/users/form", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.info("회원가입 HTML BODY : {}", response.getBody());
    }

    @Test
    public void createUser() throws Exception {
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder.urlEncodedForm().
                addParameter("name", "fleta").addParameter("userId", "ksm0814").addParameter("password", "k5696").addParameter("email", "kksm0814@naver.com").build();

        ResponseEntity<String> response = template.postForEntity("/users", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
    }

    @Test
    public void createLoginForm() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/users/loginForm", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        log.info("로그인 HTML BODY : {}", response.getBody());
    }

    @Test
    public void login() throws Exception {
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder.urlEncodedForm().
                addParameter("userId", "linkzer").addParameter("password", "k5696").build();
        ResponseEntity<String> response = template.postForEntity("/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
    }

    @Test
    public void login_fail_not_exist_user() throws Exception{
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder.urlEncodedForm().
                addParameter("userId", "notexist").addParameter("password", "k5696").build();
        ResponseEntity<String> response = template.postForEntity("/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
        log.info("실패해서 로그인창 HTML BODY : {}", response.getBody());
    }

    @Test
    public void login_fail_password() throws Exception {
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder.urlEncodedForm().
                addParameter("userId", "ksm0814").addParameter("password", "badpword").build();
        ResponseEntity<String> response = template.postForEntity("/users/login", request, String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.FOUND));
        log.info("실패해서 로그인창 HTML BODY : {}", response.getBody());
    }

}
