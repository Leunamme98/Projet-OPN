package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import com.officePharmaceutiqueNationale.OPN.model.Medicament;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicamentMapper {

    MedicamentDto toDto(Medicament medicament);
    Medicament toEntity(MedicamentDto medicamentDto);

    List<MedicamentDto> toDtoList(List<Medicament> medicaments);
    List<Medicament> toEntityList(List<MedicamentDto> medicamentDtos);
}
