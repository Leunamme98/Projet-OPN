package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.LigneCommandeMapper;
import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.model.LigneCommande;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import com.officePharmaceutiqueNationale.OPN.repository.CommandeRepository;
import com.officePharmaceutiqueNationale.OPN.repository.LigneCommandeRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.CommandeService;
import com.officePharmaceutiqueNationale.OPN.sercice.LigneCommandeService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final LigneCommandeMapper ligneCommandeMapper;
    private final CommandeService  commandeService;
    private final CommandeRepository commandeRepository;

    public LigneCommandeServiceImpl(LigneCommandeRepository ligneCommandeRepository, LigneCommandeMapper ligneCommandeMapper, CommandeService  commandeService, CommandeRepository commandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.ligneCommandeMapper = ligneCommandeMapper;
        this.commandeService = commandeService;
        this.commandeRepository = commandeRepository;
    }

    // Créer une nouvelle ligne de commande
    @Override
    public LigneCommandeDto creerLigneCommande(LigneCommandeDto ligneCommandeDto, String commandeId) {
        LigneCommande ligneCommande = ligneCommandeMapper.toEntity(ligneCommandeDto);
        ligneCommande.setCommande(commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée")));

        ligneCommande = ligneCommandeRepository.save(ligneCommande);

        // Mettre à jour le montant total de la commande après avoir ajouté la ligne de commande
        commandeService.mettreAJourMontantTotalCommande(commandeId);

        return ligneCommandeMapper.toDto(ligneCommande);
    }

    // Récupérer une ligne de commande par son ID
    @Override
    public LigneCommandeDto getLigneCommandeById(String ligneCommandeId) {
        if (ligneCommandeId == null) {
            throw new IllegalArgumentException("L'ID de la ligne de commande ne peut pas être nul");
        }

        LigneCommande ligneCommande = ligneCommandeRepository.findById(ligneCommandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de commande non trouvée avec l'ID : " + ligneCommandeId));

        return ligneCommandeMapper.toDto(ligneCommande);
    }

    // Récupérer toutes les lignes de commande associées à une commande
    @Override
    public List<LigneCommandeDto> getLignesCommandeByCommandeId(String commandeId) {
        if (commandeId == null) {
            throw new IllegalArgumentException("L'ID de la commande ne peut pas être nul");
        }

        List<LigneCommande> lignesCommande = ligneCommandeRepository.findByCommandeId(commandeId);
        return lignesCommande.stream()
                .map(ligneCommandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // Mettre à jour la quantité ou les informations d'une ligne de commande
    @Override
    public LigneCommandeDto mettreAJourQuantiteLigneCommande(String ligneCommandeId, int nouvelleQuantite) {
        // Récupération de la ligne de commande par son ID
        LigneCommande ligneCommande = ligneCommandeRepository.findById(ligneCommandeId)
                .orElseThrow(() -> new EntityNotFoundException("Ligne de commande introuvable avec ID: " + ligneCommandeId));

        // Mise à jour de la quantité
        ligneCommande.setQuantiteLigneCommande(nouvelleQuantite);

        // Sauvegarde des modifications
        LigneCommande ligneCommandeMiseAJour = ligneCommandeRepository.save(ligneCommande);

        // Récupérer l'ID de la commande associée à cette ligne de commande
        String commandeId = ligneCommande.getCommande().getId();


        // Mettre à jour le montant total de la commande après avoir modifié la ligne de commande
        commandeService.mettreAJourMontantTotalCommande(commandeId);

        // Retourner le DTO correspondant
        return ligneCommandeMapper.toDto(ligneCommandeMiseAJour);
    }


    // Méthode pour créer une LigneCommande à partir d'une LignePanier
    @Override
    public void creerLigneCommandeDepuisLignePanier(LignePanier lignePanier, String commandeId) {
        // Récupérer la commande associée à l'ID fourni
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));

        // Créer une nouvelle LigneCommande à partir de la LignePanier
        LigneCommande ligneCommande = new LigneCommande();
        ligneCommande.setArticle(lignePanier.getArticle());  // Transférer l'article depuis la ligne du panier
        ligneCommande.setQuantiteLigneCommande(lignePanier.getQuantite());  // Transférer la quantité
        ligneCommande.setCommande(commande);  // Associer la ligne de commande à la commande correspondante

        // Sauvegarder la LigneCommande
        ligneCommandeRepository.save(ligneCommande);
    }


    // Supprimer une ligne de commande par son ID
    @Override
    public void supprimerLigneCommande(String ligneCommandeId) {
        if (ligneCommandeId == null) {
            throw new IllegalArgumentException("L'ID de la ligne de commande ne peut pas être nul");
        }

        LigneCommande ligneCommande = ligneCommandeRepository.findById(ligneCommandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de commande non trouvée avec l'ID : " + ligneCommandeId));

        ligneCommandeRepository.delete(ligneCommande);
    }
}