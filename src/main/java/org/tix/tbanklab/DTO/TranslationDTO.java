package org.tix.tbanklab.DTO;

import lombok.Data;

@Data
public class TranslationDTO {
    String sourceLanguage;
    String targetLanguage;
    String textForTranslation;
}
