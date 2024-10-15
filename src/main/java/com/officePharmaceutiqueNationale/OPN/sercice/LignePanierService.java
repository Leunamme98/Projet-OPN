package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;

import java.util.List;

public interface LignePanierService {

    LignePanierDto creerLignePanier(LignePanierDto lignePanierDto);

    LignePanierDto modifierQuantiteLignePanier(String idLignePanier, int nouvelleQuantite);

    void supprimerLignePanier(String idLignePanier);

    List<LignePanierDto> recupererLignesParPanierId(String panierId);
}
