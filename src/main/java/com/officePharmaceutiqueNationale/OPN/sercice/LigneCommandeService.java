package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LigneCommandeService {

    // Enregistrer une nouvelle ligne de commande
    LigneCommandeDto enregistrerUneLigneCommande(LigneCommandeDto ligneCommandeDto);

    // Modifier une ligne de commande existante
    LigneCommandeDto modifierUneLigneCommande(LigneCommandeDto ligneCommandeDto);

    // Supprimer une ligne de commande par ID
    void supprimerUneLigneCommande(String id);

    // Récupérer une ligne de commande par son ID
    LigneCommandeDto recupererLigneCommandeParId(String idLigneCommande);

    // Récupérer toutes les lignes de commande
    List<LigneCommandeDto> recupererToutesLesLignesCommande();

    // Pagination des lignes de commande
    Page<LigneCommandeDto> recuperationParPagination(int page, int limit);

}
