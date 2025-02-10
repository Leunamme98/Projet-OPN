package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.AgentOpnDto;
import com.officePharmaceutiqueNationale.OPN.model.AgentOpn;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgentOpnMapper {

    @Mapping(target = "motDePasse", ignore = true)
    AgentOpnDto toDto(AgentOpn agentOpn);

    @Mapping(target = "motDePasseHache", ignore = true)
    AgentOpn toEntity(AgentOpnDto agentOpnDto);

    List<AgentOpnDto> toDtoList(List<AgentOpn> agentOpnList);
    List<AgentOpn> toEntityList(List<AgentOpnDto> agentOpnDtoList);
}
