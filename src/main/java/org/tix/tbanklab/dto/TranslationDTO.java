package org.tix.tbanklab.dto;

import lombok.Data;

@Data
public class TranslationDTO {
    String sourceLanguage;
    String targetLanguage;
    String textForTranslation;
}
