package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;


import java.util.List;

public interface LigneCommandeService {

    // Créer une nouvelle ligne de commande
    LigneCommandeDto creerLigneCommande(LigneCommandeDto ligneCommandeDto,String commandeId);

    // Récupérer une ligne de commande par son ID
    LigneCommandeDto getLigneCommandeById(String ligneCommandeId);

    // Récupérer toutes les lignes de commande associées à une commande
    List<LigneCommandeDto> getLignesCommandeByCommandeId(String commandeId);

    // Méthode pour mettre à jour uniquement la quantité d'une ligne de commande
    LigneCommandeDto mettreAJourQuantiteLigneCommande(String ligneCommandeId, int nouvelleQuantite);

    // Supprimer une ligne de commande par son ID
    void supprimerLigneCommande(String ligneCommandeId);

    // Méthode pour créer une ligne de commande à partir d'une ligne de panier
    void creerLigneCommandeDepuisLignePanier(LignePanier lignePanier, String commandeId);

}
