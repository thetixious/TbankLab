package org.tix.tbanklab.utils.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.tix.tbanklab.DTO.TranslationDTO;
import org.tix.tbanklab.model.TranslationRequest;

@Mapper
public interface TranslationMapper {

    @Mapping(source = "textForTranslation", target = "textForTranslation")
    TranslationRequest toEntity(TranslationDTO translationDTO);
}
