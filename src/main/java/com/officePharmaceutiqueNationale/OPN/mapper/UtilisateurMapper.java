package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.UtilisateurDto;
import com.officePharmaceutiqueNationale.OPN.model.Utilisateur;
import org.mapstruct.Mapper;

@Mapper
public interface UtilisateurMapper {

    UtilisateurDto toDto(Utilisateur utilisateur);

    Utilisateur toEntity(UtilisateurDto utilisateurDto);
}
