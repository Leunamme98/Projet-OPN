 package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.model.LigneCommande;
import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LigneCommandeMapper {

    LigneCommandeDto toDto(LigneCommande ligneCommande);

    LigneCommande toEntity(LigneCommandeDto ligneCommandeDto);
}
