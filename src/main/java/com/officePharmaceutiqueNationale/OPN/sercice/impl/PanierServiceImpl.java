package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.PanierMapper;
import com.officePharmaceutiqueNationale.OPN.mapper.LignePanierMapper;
import com.officePharmaceutiqueNationale.OPN.model.Panier;
import com.officePharmaceutiqueNationale.OPN.repository.PanierRepository;
import com.officePharmaceutiqueNationale.OPN.repository.ClientRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.PanierService;
import com.officePharmaceutiqueNationale.OPN.sercice.LigneCommandeService;
import com.officePharmaceutiqueNationale.OPN.sercice.CommandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class PanierServiceImpl implements PanierService {

    private final PanierRepository panierRepository;
    private final ClientRepository clientRepository;
    private final PanierMapper panierMapper;
    private final LignePanierMapper lignePanierMapper;
    private final CommandeService commandeService;
    private final LigneCommandeService ligneCommandeService;

    public PanierServiceImpl(PanierRepository panierRepository,
                             ClientRepository clientRepository,
                             PanierMapper panierMapper,
                             LignePanierMapper lignePanierMapper,
                             CommandeService commandeService,
                             LigneCommandeService ligneCommandeService) {
        this.panierRepository = panierRepository;
        this.clientRepository = clientRepository;
        this.panierMapper = panierMapper;
        this.lignePanierMapper = lignePanierMapper;
        this.commandeService = commandeService;
        this.ligneCommandeService = ligneCommandeService;
    }

    // Créer un nouveau panier
    @Override
    public PanierDto creerPanier(PanierDto panierDto) {
        if (panierDto == null) {
            throw new IllegalArgumentException("Le panier ne peut pas être nul");
        }
        String id = UUID.randomUUID().toString();
        Panier panier = panierMapper.toEntity(panierDto);
        panier.setId(id);

        // Sauvegarder le panier
        Panier savedPanier = panierRepository.save(panier);
        return panierMapper.toDto(savedPanier);
    }

    // Supprimer un panier par son ID
    @Override
    public void supprimerPanier(String idPanier) {
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new ResourceNotFoundException("Panier non trouvé avec l'ID : " + idPanier));
        panierRepository.delete(panier);
    }

    // Récupérer un panier par l'ID du client
    @Override
    public PanierDto recupererPanierParClientId(String clientId) {
        Panier panier = panierRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier non trouvé pour le client avec l'ID : " + clientId));
        return panierMapper.toDto(panier);
    }

    // Récupérer les lignes d'un panier
    @Override
    public List<LignePanierDto> recupererLignesPanierParPanierId(String panierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier non trouvé avec l'ID : " + panierId));
        return panier.getLignePaniers().stream()
                .map(lignePanierMapper::toDto)
                .collect(Collectors.toList());
    }

    // Valider un panier, c'est-à-dire créer une commande à partir du panier
    @Override
    public void validerPanier(String panierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new ResourceNotFoundException("Panier non trouvé avec l'ID : " + panierId));

        // Créer une commande associée au panier
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setClient(panierMapper.toDto(panier).getClientDto());

        // Créer la commande
        CommandeDto savedCommande = commandeService.creerCommande(commandeDto);

        // Pour chaque ligne du panier, créer une ligne de commande
        panier.getLignePaniers().forEach(lignePanier -> {
            ligneCommandeService.creerLigneCommandeDepuisLignePanier(lignePanier, savedCommande.getId());
        });

        // Optionnel : Suppression des lignes du panier après validation
        panier.getLignePaniers().clear();
        panierRepository.save(panier);
    }
}
