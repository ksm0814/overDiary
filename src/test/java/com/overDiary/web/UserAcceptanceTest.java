package com.overDiary.web;


import com.overDiary.support.AcceptanceTest;
import com.overDiary.support.ResponseBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

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

}
