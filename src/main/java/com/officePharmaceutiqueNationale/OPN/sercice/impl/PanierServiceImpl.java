package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.*;
import com.officePharmaceutiqueNationale.OPN.model.*;
import com.officePharmaceutiqueNationale.OPN.repository.*;
import com.officePharmaceutiqueNationale.OPN.sercice.PanierService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@AllArgsConstructor
public class PanierServiceImpl implements PanierService {


    private final ClientRepository clientRepository;
    private final PanierMapper panierMapper;
    private final LignePanierRepository lignePanierRepository;
    private final PanierRepository panierRepository;
    private final ArticleRepository articleRepository;
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private final LigneCommandeRepository ligneCommandeRepository;
    private final ClientMapperImpl clientMapper;
    private final LignePanierMapperImpl lignePanierMapper;


    // Obtenir un Panier par l'Id du client
    @Override
    public PanierDto obtenirPanierParIdClient(String idClient) {

        // Vérifier que l'ID du client n'est pas nul ou vide
        if (idClient == null || idClient.isEmpty()) {
            throw new IllegalArgumentException("L'ID du client ne peut pas être nul ou vide");
        }

        // Rechercher le client par son ID
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'ID : " + idClient));

        // Vérifier si le client a un panier
        Panier panier = client.getPanier();
        if (panier == null) {
            throw new ResourceNotFoundException("Aucun panier n'est associé au client avec l'ID : " + idClient);
        }

        // Mapper l'entité Panier à PanierDto et le retourner
        return panierMapper.toDto(panier);

    }

    // Obtenir toute les lignes de panier d'un panier
    @Override
    public List<LignePanierDto> obtenirLesLignesPanierParIdPanier(String idPanier) {
        // Récupérer les lignes du panier
        List<LignePanier> lignesPanier = lignePanierRepository.findByPanierId(idPanier);

        // Utiliser le mapper pour convertir les lignes en DTO
        return lignePanierMapper.toDtoList(lignesPanier);
    }

    // Ajouter une ligne au panier
    @Override
    public PanierDto ajouterLigneAuPanier(LignePanierDto lignePanierDto) {
        if (lignePanierDto == null || lignePanierDto.getArticle() == null || lignePanierDto.getArticle().getId() == null) {
            throw new IllegalArgumentException("Le DTO de la ligne de panier et l'article doivent être spécifiés.");
        }

        // Récupérer le panier et l'article
        Panier panier = panierRepository.findById(lignePanierDto.getPanier().getId())
                .orElseThrow(() -> new IllegalArgumentException("Panier non trouvé."));
        Article article = articleRepository.findById(lignePanierDto.getArticle().getId())
                .orElseThrow(() -> new IllegalArgumentException("Article non trouvé."));

        // Vérifier si l'article existe déjà dans le panier
        LignePanier ligneExistante = lignePanierRepository.findByPanierAndArticle(panier, article);

        if (ligneExistante != null) {
            // Mettre à jour la quantité de la ligne existante
            ligneExistante.setQuantite(ligneExistante.getQuantite() + lignePanierDto.getQuantite());
            lignePanierRepository.save(ligneExistante);
        } else {
            // Ajouter une nouvelle ligne au panier
            LignePanier nouvelleLignePanier = new LignePanier();
            nouvelleLignePanier.setId(UUID.randomUUID().toString());
            nouvelleLignePanier.setArticle(article);
            nouvelleLignePanier.setQuantite(lignePanierDto.getQuantite());
            nouvelleLignePanier.setDateAjout(LocalDateTime.now());
            nouvelleLignePanier.setPanier(panier);
            lignePanierRepository.save(nouvelleLignePanier);

            // Mettre à jour le nombre d'articles dans le panier
            panier.setNombreArticle(panier.getNombreArticle() + 1);
        }

        // Mettre à jour le prix total du panier
        panier.setPrixTotalPanier(panier.getPrixTotalPanier() + article.getPrixUnitaire() * lignePanierDto.getQuantite());

        // Sauvegarder les modifications du panier
        panierRepository.save(panier);

        return panierMapper.toDto(panier);
    }

    // Modifier une ligne dans un panier
    @Override
    public PanierDto modifierLignePanier(LignePanierDto lignePanierDto) {
        if (lignePanierDto == null || lignePanierDto.getQuantite() <= 0) {
            throw new IllegalArgumentException("Le DTO de la ligne de panier ne peut pas être nul et la quantité doit être positive.");
        }

        LignePanier lignePanier = lignePanierRepository.findById(lignePanierDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de panier non trouvée avec l'ID : " + lignePanierDto.getId()));

        Panier panier = lignePanier.getPanier();
        Article article = lignePanier.getArticle();
        int differenceQuantite = lignePanierDto.getQuantite() - lignePanier.getQuantite();

        // Mise à jour de la ligne panier
        lignePanier.setQuantite(lignePanierDto.getQuantite());
        lignePanier.setDateAjout(LocalDateTime.now());

        // Mise à jour du prix total du panier
        panier.setPrixTotalPanier(panier.getPrixTotalPanier() + differenceQuantite * article.getPrixUnitaire());

        // Sauvegarde des modifications
        lignePanierRepository.save(lignePanier);
        panierRepository.save(panier);

        return panierMapper.toDto(panier);
    }

    // Supprimer une ligne de panier d'un panier
    @Override
    public PanierDto supprimerLignePanier(String idLignePanier) {
        // Vérifier si l'ID de la ligne de panier est nul ou vide
        if (idLignePanier == null || idLignePanier.isEmpty()) {
            throw new IllegalArgumentException("L'ID de la ligne de panier ne peut pas être nul ou vide");
        }

        // Récupérer la ligne de panier par son ID ou lever une exception si elle n'existe pas
        LignePanier lignePanier = lignePanierRepository.findById(idLignePanier)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de panier non trouvée avec l'ID : " + idLignePanier));

        // Récupérer le panier auquel la ligne appartient
        Panier panier = lignePanier.getPanier();

        // Mettre à jour le nombre d'articles et le prix total du panier
        panier.setNombreArticle(panier.getNombreArticle() - 1);
        panier.setPrixTotalPanier(panier.getPrixTotalPanier() - (lignePanier.getArticle().getPrixUnitaire() * lignePanier.getQuantite()));

        // Sauvegarder le panier mis à jour
        panierRepository.save(panier);

        // Supprimer la ligne de panier
        lignePanier.setPanier(null);
        lignePanierRepository.delete(lignePanier);

        // Retourner le panier mis à jour sous forme de DTO
        return panierMapper.toDto(panier);
    }

    // Valider un panier en le transformant en commande
    @Override
    public CommandeDto validerPanier(String idPanier) {

        // Récupérer le panier par son ID ou lever une exception si non trouvé
        Panier panier = panierRepository.findById(idPanier)
                .orElseThrow(() -> new ResourceNotFoundException("Panier non trouvé avec l'ID : " + idPanier));

        // Créer une nouvelle commande
        CommandeDto commandeDto = new CommandeDto();
        commandeDto.setNumeroCommande(UUID.randomUUID().toString());
        commandeDto.setMontantTotalCommande(panier.getPrixTotalPanier());
        commandeDto.setDateCommande(LocalDateTime.now());
        commandeDto.setEtatCommande(EtatCommande.EN_ATTENTE);

        // Récupérer le client associé au panier
        Client client = clientRepository.findById(panier.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client non trouvé avec l'ID : " + panier.getClientId()));
        commandeDto.setClient(clientMapper.toDto(client));

        // Récupérer les lignes de panier associées
        List<LignePanier> lignesPanier = lignePanierRepository.findByPanierId(idPanier);

        // Créer les lignes de commande à partir des lignes de panier et les sauvegarder
        Commande commande = commandeMapper.toEntity(commandeDto);
        for (LignePanier lignePanier : lignesPanier) {
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setId(UUID.randomUUID().toString());
            ligneCommande.setArticle(lignePanier.getArticle());
            ligneCommande.setQuantiteLigneCommande(lignePanier.getQuantite());
            ligneCommande.setCommande(commande);

            ligneCommandeRepository.save(ligneCommande);

            // Supprimer la ligne de panier après transformation en ligne de commande
            lignePanier.setPanier(null);
            lignePanierRepository.delete(lignePanier);
        }

        // Sauvegarder la commande
        commandeRepository.save(commande);

        // Retourner le DTO de la commande
        return commandeMapper.toDto(commande);
    }


}
