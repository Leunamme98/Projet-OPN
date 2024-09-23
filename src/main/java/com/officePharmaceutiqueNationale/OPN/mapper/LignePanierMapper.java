 package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper; 
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;

@Mapper
public interface LignePanierMapper {

   
    LignePanierDto toDto(LignePanier lignePanier);
    
     
    LignePanier toEntity(LignePanierDto lignePanierDto);
}
