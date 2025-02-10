package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DispositifMedicalMapper {

    DispositifMedicalDto toDto(DispositifMedical dispositifMedical);
    DispositifMedical toEntity(DispositifMedicalDto dto);

    List<DispositifMedicalDto> toDtoList(List<DispositifMedical> dispositifsMedicaux);
    List<DispositifMedical> toEntityList(List<DispositifMedicalDto> dispositifsMedicauxDtos);
}
