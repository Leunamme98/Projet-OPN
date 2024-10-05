 package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper;
import com.officePharmaceutiqueNationale.OPN.dto.FormeGaleniqueDto;
import com.officePharmaceutiqueNationale.OPN.model.FormeGalenique;

@Mapper(componentModel = "spring")
public interface FormeGaleniqueMapper {

    FormeGaleniqueDto toDto(FormeGalenique formeGalenique);
    FormeGalenique toEntity(FormeGaleniqueDto formeGaleniqueDto);

}
