package org.tix.tbanklab.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.tix.tbanklab.dto.TranslationDTO;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class APIService {
    private final int COUNT_OF_THREADS = 10;
    private final RestTemplate restTemplate;
    private final String apiUrl = "https://translate.api.cloud.yandex.net/translate/v2/translate";
    private final String apiKey = "AQVNw7HEX3MGLckKsXzTIn-QmcHTptDfMi56g6wB";

    public APIService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .defaultHeader("Authorization", "Api-Key " + apiKey)
                .build();
    }

    public String translateWord(String sourceLanguage, String targetLanguage, String word) {
        Map<String, Object> body = new HashMap<>();
        body.put("sourceLanguageCode", sourceLanguage);
        body.put("targetLanguageCode", targetLanguage);
        body.put("texts", Collections.singletonList(word));

        ResponseEntity<Map> response = restTemplate.postForEntity(apiUrl, body, Map.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            List<Map<String, String>> translations = (List<Map<String, String>>) response.getBody().get("translations");
            return translations.get(0).get("text");
        } else {
            throw new RuntimeException("Translation failed");
        }
    }

    public String translate(TranslationDTO translationDTO) {
        String sourceLanguage = translationDTO.getSourceLanguage();
        String targetLanguage = translationDTO.getTargetLanguage();
        String text = translationDTO.getTextForTranslation();
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_OF_THREADS);

        List<Future<String>> futures = new ArrayList<>();
        List<String> words = Arrays.asList(text.split("\\s+"));
        StringBuilder stringBuilder = new StringBuilder();

        for (String word : words) {
            futures.add(executorService.submit(() -> translateWord(sourceLanguage, targetLanguage, word)));
        }
        for (Future<String> future : futures) {
            try {
                stringBuilder.append(future.get()+ " ");
            } catch (Exception e) {
                stringBuilder.append("Error " + e.getMessage());
            }
        }
        return stringBuilder.toString();

    }
}
