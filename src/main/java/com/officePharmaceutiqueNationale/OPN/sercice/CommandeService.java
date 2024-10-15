package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;

import java.util.List;

public interface CommandeService {

    // Créer une commande
    CommandeDto creerCommande(CommandeDto commandeDto);

    // Récupérer une commande par ID
    CommandeDto getCommandeById(String commandeId);

    // Récupérer toutes les commandes
    List<CommandeDto> getAllCommandes();

    // Récupérer toutes les commandes d'un client
    List<CommandeDto> getCommandesByClientId(String clientId);

    // Mettre à jour l'état d'une commande (ex: En Cours, Terminé, etc.)
    CommandeDto mettreAJourEtatCommande(String commandeId, String nouvelEtat);

    // Supprimer une commande par ID
    void supprimerCommande(String commandeId);

    void mettreAJourMontantTotalCommande(String commandeId);
}
