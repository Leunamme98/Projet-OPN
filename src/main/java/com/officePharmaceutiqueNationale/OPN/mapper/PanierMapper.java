package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.model.Panier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PanierMapper {

    PanierDto toDto(Panier panier);
    Panier toEntity(PanierDto panierDto);

    List<PanierDto> toDtoList(List<Panier> panierList);
    List<Panier> toEntityList(List<PanierDto> panierDtoList);

}
