 package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpecialitePharmaceutiqueMapper {

    SpecialitePharmaceutiqueDto toDto(SpecialitePharmaceutique specialitePharmaceutique);

    SpecialitePharmaceutique toEntity(SpecialitePharmaceutiqueDto specialitePharmaceutiqueDto);

    List<SpecialitePharmaceutiqueDto> toDtoList(List<SpecialitePharmaceutique> specialitePharmaceutiques);
    List<SpecialitePharmaceutique> toEntityList(List<SpecialitePharmaceutiqueDto> specialitePharmaceutiqueDto);

}
