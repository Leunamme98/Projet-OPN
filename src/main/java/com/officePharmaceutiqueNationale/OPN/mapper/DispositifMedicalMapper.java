package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DispositifMedicalMapper {

    DispositifMedicalDto toDto(DispositifMedical dispositifMedical);
    DispositifMedical toEntity(DispositifMedicalDto dto);

}
