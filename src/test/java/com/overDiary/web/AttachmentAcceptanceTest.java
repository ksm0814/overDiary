package com.overDiary.web;

import com.overDiary.support.AcceptanceTest;
import com.overDiary.support.ResponseBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.util.MultiValueMap;

public class AttachmentAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(AttachmentAcceptanceTest.class);

    @Test
    public void create() throws Exception {
        log.info("파일 정보 : {}", new ClassPathResource("import.sql").getURL());
        HttpEntity<MultiValueMap<String, Object>> request = ResponseBuilder
                .multipartFormData()
                .addParameter("file", new ClassPathResource("import.sql"))
                .build();
        template().postForEntity("/api/attachments", request, String.class);
    }
}
