package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import com.officePharmaceutiqueNationale.OPN.model.LigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ArticleMapper.class, PanierMapper.class, CommandeMapper.class})
public interface LignePanierMapper {

    // Conversion LignePanier <-> LignePanierDto
    LignePanierDto toDto(LignePanier lignePanier);
    LignePanier toEntity(LignePanierDto lignePanierDto);

    List<LignePanierDto> toDtoList(List<LignePanier> lignePaniers);
    List<LignePanier> toEntityList(List<LignePanierDto> lignePanierDtos);

    // Conversion LignePanier <-> LigneCommande
    @Mapping(target = "quantiteLigneCommande", source = "quantite")
    @Mapping(target = "commande", ignore = true)
    @Mapping(target = "id", ignore = true)
    LigneCommande toLigneCommande(LignePanier lignePanier);

    @Mapping(target = "quantiteLigneCommande", source = "quantite")
    @Mapping(target = "commande", ignore = true)
    LigneCommandeDto toLigneCommandeDto(LignePanier lignePanier);

    List<LigneCommande> toLigneCommandeList(List<LignePanier> lignePaniers);
}
