package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper;
import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import com.officePharmaceutiqueNationale.OPN.model.Dci;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DciMapper {

    DciDto toDto(Dci dci);
    Dci toEntity(DciDto dciDto);

    List<DciDto> toDtoList(List<Dci> dcis);
    List<Dci> toEntityList(List<DciDto> dcis);
}
