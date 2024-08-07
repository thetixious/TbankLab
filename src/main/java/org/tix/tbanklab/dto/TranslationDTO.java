package org.tix.tbanklab.dto;

import lombok.Data;

@Data

public class TranslationDTO {
    String sourceLanguage;
    String targetLanguage;
    String textForTranslation;

    public TranslationDTO(String sourceLanguage, String targetLanguage, String textForTranslation) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.textForTranslation = textForTranslation;
    }

    public TranslationDTO() {
    }
}

