 package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper; 
import com.officePharmaceutiqueNationale.OPN.dto.LigneLivraisonDto;
import com.officePharmaceutiqueNationale.OPN.model.LigneLivraison;

@Mapper
public interface LigneLivraisonMapper {

     
    LigneLivraisonDto toDto(LigneLivraison ligneLivraison);
    
    
    LigneLivraison toEntity(LigneLivraisonDto ligneLivraisonDto);
}
