package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;

import java.util.List;

public interface PanierService {

    PanierDto obtenirPanierParIdClient(String idClient);
    List<LignePanierDto> obtenirLesLignesPanierParIdPanier(String idPanier);
    PanierDto ajouterLigneAuPanier(LignePanierDto lignePanierDto);
    PanierDto modifierLignePanier(LignePanierDto lignePanierDto);
    PanierDto supprimerLignePanier(String idLignePanier);
    CommandeDto validerPanier(String idPanier);

}
