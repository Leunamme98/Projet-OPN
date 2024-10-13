package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import com.officePharmaceutiqueNationale.OPN.model.LigneCommande;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LigneCommandeMapper {

    LigneCommandeDto toDto(LigneCommande ligneCommande);
    LigneCommande toEntity(LigneCommandeDto ligneCommandeDto);

    List<LigneCommandeDto> toDtoList(List<LigneCommande> ligneCommandeList);
    List<LigneCommande> toEntityList(List<LigneCommande> ligneCommandeList);

}
