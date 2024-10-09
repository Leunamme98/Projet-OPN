package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.ClientDto;
import com.officePharmaceutiqueNationale.OPN.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "motDePasse", ignore = true)
    ClientDto toDto(Client client);

    @Mapping(target = "motDePasseHache", ignore = true)
    Client toEntity(ClientDto clientDto);

    List<ClientDto> toDtoList(List<Client> clientList);
    List<Client> toEntityList(List<ClientDto> clientDtoList);
}
