package org.tix.tbanklab.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
public class APIService {
    private RestTemplate restTemplate;
    private String apiUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private String apiKey = "AQVNw7HEX3MGLckKsXzTIn-QmcHTptDfMi56g6wB";

    public APIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .defaultHeader("Authorization","Api-Key " + apiKey)
                .build();
    }
    public String translate(String sourceLanguage, String targetLanguage, String word){
        Map<String,Object> body = new HashMap<>();
        body.put("sourceLanguageCode", sourceLanguage);
        body.put("targetLanguageCode", targetLanguage);
        body.put("texts", Collections.singletonList(word));

        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl,body,Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Map<String, String>> translations = (List<Map<String, String>>) response.getBody().get("translations");
            return translations.get(0).get("text");
        } else {
            throw new RuntimeException("Translation failed");
        }
    }

}
