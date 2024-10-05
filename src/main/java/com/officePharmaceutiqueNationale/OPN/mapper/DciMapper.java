 package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper;
import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import com.officePharmaceutiqueNationale.OPN.model.Dci;
import org.mapstruct.Mapping;

 @Mapper(componentModel = "spring")
public interface DciMapper {

    DciDto toDto(Dci dci);
    Dci toEntity(DciDto dciDto);
}
