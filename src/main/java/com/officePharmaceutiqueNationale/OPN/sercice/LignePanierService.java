package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;


public interface LignePanierService {

    LignePanierDto ajouterLignePanier(LignePanierDto lignePanierDto);
    LignePanierDto modifierLignePanier(LignePanierDto lignePanierDto);
    void supprimerLignePanier(String idLignePanier);
    LignePanierDto obtenirLignePanierParId(String lignePanier);

}
