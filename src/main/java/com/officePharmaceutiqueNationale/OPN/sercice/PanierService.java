package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;

import java.util.List;

public interface PanierService {

    PanierDto creerPanier(PanierDto panierDto);

    void supprimerPanier(String idPanier);

    PanierDto recupererPanierParClientId(String clientId);

    List<LignePanierDto> recupererLignesPanierParPanierId(String panierId);

    void validerPanier(String panierId);
}
