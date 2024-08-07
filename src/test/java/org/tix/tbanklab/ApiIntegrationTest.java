package org.tix.tbanklab;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tix.tbanklab.dto.TranslationDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiIntegrationTest {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testTranslation(){
        ResponseEntity<?> response = testRestTemplate.postForEntity("/translate", new TranslationDTO("en", "es", "hello"), String.class);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("hola", response.getBody().toString().trim());
    }

}
