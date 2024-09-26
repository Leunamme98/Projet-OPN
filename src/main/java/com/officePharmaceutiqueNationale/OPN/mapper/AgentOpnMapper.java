 package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.AgentOpnDto;
import com.officePharmaceutiqueNationale.OPN.model.AgentOpn;
import org.mapstruct.Mapper;
 

@Mapper
public interface AgentOpnMapper {
     
    AgentOpnDto toDto(AgentOpn agentOpn);
    AgentOpn toEntity(AgentOpnDto agentOpnDto);

}
