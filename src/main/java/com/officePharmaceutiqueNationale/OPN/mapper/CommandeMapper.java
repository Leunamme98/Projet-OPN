package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.model.Dci;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    CommandeDto toDto(Commande commande);
    Commande toEntity(CommandeDto commandeDto);

    List<CommandeDto> toDtoList(List<Commande> commandes);
    List<Commande> toEntityList(List<CommandeDto> commandeDtos);

}
