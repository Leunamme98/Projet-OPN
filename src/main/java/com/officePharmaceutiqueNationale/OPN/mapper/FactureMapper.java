 package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper; 
import com.officePharmaceutiqueNationale.OPN.dto.FactureDto;
import com.officePharmaceutiqueNationale.OPN.model.Facture;

@Mapper
public interface FactureMapper {

     
    FactureDto toDto(Facture facture);
    
    
    Facture toEntity(FactureDto factureDto);
}
