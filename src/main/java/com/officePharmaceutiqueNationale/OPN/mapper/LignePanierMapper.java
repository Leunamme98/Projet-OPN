package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ArticleMapper.class, PanierMapper.class})
public interface LignePanierMapper {

    LignePanierDto toDto(LignePanier lignePanier);
    LignePanier toEntity(LignePanierDto lignePanierDto);

    List<LignePanierDto> toDtoList(List<LignePanier> lignePaniers);
    List<LignePanier> toEntityList(List<LignePanierDto> lignePanierDtos);

}