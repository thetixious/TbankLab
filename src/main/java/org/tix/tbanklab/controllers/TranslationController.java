package org.tix.tbanklab.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.tix.tbanklab.dto.TranslationDTO;
import org.tix.tbanklab.service.TranslationService;

@Controller
public class TranslationController {
    private final TranslationService translationService;

    public TranslationController(TranslationService translationService) {
        this.translationService = translationService;
    }

    @PostMapping("/translate")
    public ResponseEntity<?> translate(@RequestBody TranslationDTO translationDTO, HttpServletRequest request){
        translationService.translate(translationDTO,request);
        return ResponseEntity.ok(request.getRemoteAddr());
    }

}
