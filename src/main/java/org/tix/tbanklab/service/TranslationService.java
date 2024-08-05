package org.tix.tbanklab.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.tix.tbanklab.dto.TranslationDTO;
import org.tix.tbanklab.model.TranslationRequest;
import org.tix.tbanklab.repo.TranslationRepository;
import org.tix.tbanklab.utils.mappers.TranslationMapper;

@Service
public class TranslationService {
    private final TranslationMapper translationMapper;
    private final TranslationRepository translationRepository;
    private final APIService apiService;

    public TranslationService(TranslationMapper translationMapper, TranslationRepository translationRepository, APIService apiService) {
        this.translationMapper = translationMapper;
        this.translationRepository = translationRepository;
        this.apiService = apiService;
    }

    public TranslationRequest requestPreparation(TranslationDTO translationDTO, HttpServletRequest request){
        TranslationRequest translationRequest = translationMapper.toEntity(translationDTO);
        translationRequest.setAddressRequest(request.getRemoteAddr().toString());
        return translationRequest;
    }

    public void translate(TranslationDTO translationDTO, HttpServletRequest request) {
        TranslationRequest translationRequest = requestPreparation(translationDTO,request);
        translationRequest.setResultText(apiService.translate(translationDTO.getSourceLanguage(),translationDTO.getTargetLanguage(),translationDTO.getTextForTranslation()));
        translationRepository.save(translationRequest);
        System.out.println(translationRequest.getAddressRequest() + " " + translationRequest.getTextForTranslation() + " " + translationRequest.getResultText());

    }
}
