package org.tix.tbanklab.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.tix.tbanklab.DTO.TranslationDTO;
import org.tix.tbanklab.model.TranslationRequest;
import org.tix.tbanklab.repo.TranslationRepository;
import org.tix.tbanklab.utils.mappers.TranslationMapper;

@Service
public class TranslationService {
    private final TranslationMapper translationMapper;
    private final TranslationRepository translationRepository;

    public TranslationService(TranslationMapper translationMapper, TranslationRepository translationRepository) {
        this.translationMapper = translationMapper;
        this.translationRepository = translationRepository;
    }

    public TranslationRequest requestPreparation(TranslationDTO translationDTO, HttpServletRequest request){
        TranslationRequest translationRequest = translationMapper.toEntity(translationDTO);
        translationRequest.setAddressRequest(request.getRemoteAddr().toString());
        return translationRequest;
    }

    public void translate(TranslationDTO translationDTO, HttpServletRequest request) {
        TranslationRequest translationRequest = requestPreparation(translationDTO,request);
        translationRequest.setResultText("Привет, мир!");
        translationRepository.save(translationRequest);
        System.out.println(translationRequest.getAddressRequest() + " " + translationRequest.getTextForTranslation() + " " + translationRequest.getResultText());

    }
}
