package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper; 
import com.officePharmaceutiqueNationale.OPN.dto.ReclamationDto;
import com.officePharmaceutiqueNationale.OPN.model.Reclamation;

@Mapper
public interface ReclamationMapper {

    
    ReclamationDto toDto(Reclamation reclamation);
    
 
    Reclamation toEntity(ReclamationDto reclamationDto);
}
