package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper; 
import com.officePharmaceutiqueNationale.OPN.dto.LivraisonDto;
import com.officePharmaceutiqueNationale.OPN.model.Livraison;

@Mapper
public interface LivraisonMapper {

    
    LivraisonDto toDto(Livraison livraison);
 
    Livraison toEntity(LivraisonDto livraisonDto);
}
 