package com.overDiary.support;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;

public class ResponseBuilder {
    private HttpHeaders headers;
    private MultiValueMap<String, Object> params;

    private ResponseBuilder(HttpHeaders headers) {
        this.headers = headers;
        this.params = new LinkedMultiValueMap<>();
    }

    public ResponseBuilder addParameter(String key, Long value) {
        this.params.add(key, value + "");
        return this;
    }

    public ResponseBuilder addParameter(String key, Object value) {
        this.params.add(key, value);
        return this;
    }

    public ResponseBuilder put() {
        params.add("_method", "put");
        return this;
    }

    public ResponseBuilder delete() {
        params.add("_method", "delete");
        return this;
    }

    public HttpEntity<MultiValueMap<String, Object>> build() {
        return new HttpEntity<MultiValueMap<String, Object>>(params, headers);
    }

    public static ResponseBuilder urlEncodedForm() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new ResponseBuilder(headers);
    }

    public static ResponseBuilder multipartFormData() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new ResponseBuilder(headers);
    }
}
