package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper;
import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.model.Panier;

@Mapper
public interface PanierMapper {

    PanierDto toDto(Panier panier);
    
    Panier toEntity(PanierDto panierDto);
}
 