package com.overDiary.web;

import com.overDiary.support.AcceptanceTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RankAcceptanceTest extends AcceptanceTest {

    @Test
    public void createHighRankerList() throws Exception{
        ResponseEntity<String> response = template.getForEntity("/ranks", String.class);
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }
}
