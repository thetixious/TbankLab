package org.tix.tbanklab.model;

import lombok.Data;

@Data
public class TranslationRequest {
    String addressRequest;
    String textForTranslation;
    String resultText;
}
