 package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


 @Mapper(componentModel = "spring")
public interface SpecialitePharmaceutiqueMapper {

    SpecialitePharmaceutiqueDto toDto(SpecialitePharmaceutique specialitePharmaceutique);
    SpecialitePharmaceutique toEntity(SpecialitePharmaceutiqueDto specialitePharmaceutiqueDto);

}
