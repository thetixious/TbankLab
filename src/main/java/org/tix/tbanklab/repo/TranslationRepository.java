package org.tix.tbanklab.repo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.tix.tbanklab.model.TranslationRequest;

@Repository
public class TranslationRepository {
    private final JdbcTemplate jdbcTemplate;

    public TranslationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(TranslationRequest request){
        return jdbcTemplate.update("INSERT INTO translation_request(ip_address, translation_text, result_text) VALUES (?,?,?)",
                request.getAddressRequest(),request.getTextForTranslation(), request.getResultText());
    }

}
