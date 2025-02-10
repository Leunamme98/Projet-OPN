package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.model.Article;
import com.officePharmaceutiqueNationale.OPN.model.Panier;
import com.officePharmaceutiqueNationale.OPN.mapper.LignePanierMapperImpl;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import com.officePharmaceutiqueNationale.OPN.repository.ArticleRepository;
import com.officePharmaceutiqueNationale.OPN.repository.LignePanierRepository;
import com.officePharmaceutiqueNationale.OPN.repository.PanierRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.LignePanierService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LignePanierServiceImpl implements LignePanierService {

    private final LignePanierMapperImpl lignePanierMapper;
    private final LignePanierRepository lignePanierRepository;
    private final PanierRepository panierRepository;
    private final ArticleRepository articleRepository;


    @Override
    public LignePanierDto ajouterLignePanier(@Valid LignePanierDto lignePanierDto) {

        // Vérification des données du DTO
        if (lignePanierDto == null || lignePanierDto.getQuantite() <= 0) {
            throw new IllegalArgumentException("Le DTO de la ligne de panier ne doit pas être nul et la quantité doit être positive");
        }

        // Génération d'un UUID pour l'identifiant de la ligne de panier
        String lignePanierId = UUID.randomUUID().toString();

        // Transformation du DTO en entité LignePanier
        LignePanier lignePanier = lignePanierMapper.toEntity(lignePanierDto);
        lignePanier.setId(lignePanierId);

        // Vérification de l'existence de l'article
        if (lignePanierDto.getArticle() != null && lignePanierDto.getArticle().getId() != null) {
            Optional<Article> optionalArticle = articleRepository.findById(lignePanierDto.getArticle().getId());
            Article article = optionalArticle.orElseThrow(() ->
                    new IllegalArgumentException("L'article avec l'ID " + lignePanierDto.getArticle().getId() + " n'existe pas.")
            );

            // Mettre à jour la référence de l'article
            lignePanier.setArticle(article);
        } else {
            throw new IllegalArgumentException("L'article est requis pour ajouter une ligne de panier.");
        }

        // Vérification de l'existence du panier
        if (lignePanierDto.getPanier() != null && lignePanierDto.getPanier().getId() != null) {
            Optional<Panier> optionalPanier = panierRepository.findById(lignePanierDto.getPanier().getId());
            Panier panier = optionalPanier.orElseThrow(() ->
                    new IllegalArgumentException("Le panier avec l'ID " + lignePanierDto.getPanier().getId() + " n'existe pas.")
            );

            // Mettre à jour la référence du panier
            lignePanier.setPanier(panier);
        } else {
            throw new IllegalArgumentException("Le panier est requis pour ajouter une ligne de panier.");
        }

        // Calculer le sous-total
        lignePanier.setDateAjout(LocalDateTime.now());

        // Enregistrer la ligne de panier
        LignePanier savedLignePanier = lignePanierRepository.save(lignePanier);

        // Retourner le DTO de la ligne de panier sauvegardée
        return lignePanierMapper.toDto(savedLignePanier);
    }


    // Modifier une ligne de panier
    @Override
    public LignePanierDto modifierLignePanier(@Valid LignePanierDto lignePanierDto) {

        // Vérification que le DTO et l'ID sont valides
        if (lignePanierDto == null || lignePanierDto.getId() == null || lignePanierDto.getId().isBlank()) {
            throw new IllegalArgumentException("Le DTO de la ligne de panier ne doit pas être nul et doit contenir un ID valide.");
        }

        // Récupérer la ligne de panier existante
        LignePanier lignePanier = lignePanierRepository.findById(lignePanierDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de panier non trouvée avec l'ID : " + lignePanierDto.getId()));


        // Mise à jour de la quantité
        lignePanier.setQuantite(lignePanierDto.getQuantite());


        // Mise à jour de la date d'ajout avec la nouvelle date
        lignePanier.setDateAjout(LocalDateTime.now());

        // Récupérer le panier associé
        Panier panier = lignePanier.getPanier();

        // Sauvegarde du panier mis à jour
        panierRepository.save(panier);

        // Sauvegarde de la ligne de panier modifiée
        lignePanier = lignePanierRepository.save(lignePanier);

        // Retourner le DTO mis à jour
        return lignePanierMapper.toDto(lignePanier);
    }

    // Supprimer une ligne de panier
    @Override
    public void supprimerLignePanier(String idLignePanier) {

        // Vérifier que l'ID de la ligne de panier n'est pas nul ou vide
        if (idLignePanier == null || idLignePanier.isEmpty()) {
            throw new IllegalArgumentException("L'ID de la ligne de panier ne peut pas être nul ou vide");
        }

        // Récupérer la ligne de panier existante par son ID
        LignePanier lignePanierExistante = lignePanierRepository.findById(idLignePanier)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de panier avec ID " + idLignePanier + " non trouvée"));

        // Récupérer le panier associé
        Panier panier = lignePanierExistante.getPanier();

        // Vérification si le panier existe
        if (panier == null) {
            throw new ResourceNotFoundException("Aucun panier associé à cette ligne de panier");
        }

        // Mettre à jour le nombre total d'articles dans le panier
        panier.setNombreArticle(panier.getNombreArticle() - lignePanierExistante.getQuantite());

        // Sauvegarder les modifications du panier
        panierRepository.save(panier);

        // Supprimer la ligne de panier
        lignePanierRepository.delete(lignePanierExistante);
    }

    @Override
    public LignePanierDto obtenirLignePanierParId(String lignePanierId) {

        // Vérifier que l'ID de la ligne de panier n'est pas nul ou vide
        if (lignePanierId == null || lignePanierId.isEmpty()) {
            throw new IllegalArgumentException("L'ID de la ligne de panier ne peut pas être nul ou vide");
        }

        // Récupérer la ligne de panier par son ID
        LignePanier lignePanier = lignePanierRepository.findById(lignePanierId)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de panier avec ID " + lignePanierId + " non trouvée"));

        // Convertir la ligne de panier en DTO
        return lignePanierMapper.toDto(lignePanier);
    }

}
