 package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper;
import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;

@Mapper
public interface DispositifMedicalMapper {

    DispositifMedicalDto toDto(DispositifMedical dispositifMedical);
    
    DispositifMedical toEntity(DispositifMedicalDto dispositifMedicalDto);
}
