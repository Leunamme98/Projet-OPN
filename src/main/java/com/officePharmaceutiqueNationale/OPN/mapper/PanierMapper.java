package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;

import com.officePharmaceutiqueNationale.OPN.model.Panier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PanierMapper {

    PanierDto toDto(Panier panier);
    Panier toEntity(PanierDto panierDto);

    List<PanierDto> toDtoList(List<Panier> paniers);
    List<Panier> toEntityList(List<PanierDto> panierDtos);

}
