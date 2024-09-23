package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    CommandeDto toDto(Commande commande);

    Commande toEntity(CommandeDto commandeDto);
}
