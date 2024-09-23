 package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import org.mapstruct.Mapper;
 

@Mapper
public interface SpecialitePharmaceutiqueMapper {

     
    SpecialitePharmaceutiqueDto toDto(SpecialitePharmaceutique specialitePharmaceutique);

     
    SpecialitePharmaceutique toEntity(SpecialitePharmaceutiqueDto specialitePharmaceutiqueDto);
}
