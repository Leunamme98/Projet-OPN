package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.LigneCommandeDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.LigneCommandeMapper;
import com.officePharmaceutiqueNationale.OPN.model.Article;
import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.model.LigneCommande;
import com.officePharmaceutiqueNationale.OPN.repository.ArticleRepository;
import com.officePharmaceutiqueNationale.OPN.repository.CommandeRepository;
import com.officePharmaceutiqueNationale.OPN.repository.LigneCommandeRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.LigneCommandeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final LigneCommandeMapper ligneCommandeMapper;
    private final ArticleRepository articleRepository;
    private final CommandeRepository commandeRepository;

    @Override
    public LigneCommandeDto enregistrerUneLigneCommande(LigneCommandeDto ligneCommandeDto) {

        // Vérifier l'existence de l'article
        Article article = articleRepository.findById(ligneCommandeDto.getArticle().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Article avec ID " + ligneCommandeDto.getArticle().getId() + " non trouvé."));

        // Vérifier l'existence de la commande
        Commande commande = commandeRepository.findById(ligneCommandeDto.getCommande().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Commande avec ID " + ligneCommandeDto.getCommande().getId() + " non trouvée."));

        // Vérifier si la quantité demandée est disponible dans le stock
        if (ligneCommandeDto.getQuantiteLigneCommande() > article.getQuantiteStock()) {
            throw new IllegalArgumentException("Quantité demandée supérieure à la quantité en stock de l'article.");
        }

        // Mettre à jour l'attribut isDeleted de l'article
        article.setIsDeleted(false);

        // Calculer le prix total de la ligne de commande
        double prixTotal = ligneCommandeDto.getQuantiteLigneCommande() * article.getPrixGenerique();

        // Mettre à jour la quantité en stock de l'article
        article.setQuantiteStock(article.getQuantiteStock() - ligneCommandeDto.getQuantiteLigneCommande());
        articleRepository.save(article);

        // Créer une nouvelle ligne de commande
        LigneCommande ligneCommande = ligneCommandeMapper.toEntity(ligneCommandeDto);
        ligneCommande.setId(UUID.randomUUID().toString());
        ligneCommande.setPrixLigneCommande(prixTotal);

        // Enregistrer la nouvelle ligne de commande dans la base de données
        LigneCommande savedLigneCommande = ligneCommandeRepository.save(ligneCommande);
        return ligneCommandeMapper.toDto(savedLigneCommande);

    }

    @Override
    public LigneCommandeDto modifierUneLigneCommande(LigneCommandeDto ligneCommandeDto) {
        return null;
    }

    @Override
    public void supprimerUneLigneCommande(String id) {
        // Vérifier l'existence de la ligne de commande
        LigneCommande ligneCommande = ligneCommandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de commande avec ID " + id + " non trouvée."));

        // Vérifier si la ligne de commande est liée à une commande d'article
        if (ligneCommande.getCommande() != null) {
            throw new IllegalArgumentException("La ligne de commande avec ID " + id + " ne peut pas être supprimée car elle est liée à une commande d'article.");
        }

        ligneCommandeRepository.delete(ligneCommande);
    }

    @Override
    public LigneCommandeDto recupererLigneCommandeParId(String idLigneCommande) {
        // Vérifier l'existence de la ligne de commande
        LigneCommande ligneCommande = ligneCommandeRepository.findById(idLigneCommande)
                .orElseThrow(() -> new ResourceNotFoundException("Ligne de commande avec ID " + idLigneCommande + " non trouvée."));
        return ligneCommandeMapper.toDto(ligneCommande);
    }

    @Override
    public List<LigneCommandeDto> recupererToutesLesLignesCommande() {
        List<LigneCommande> lignesCommande = ligneCommandeRepository.findAll();
        return ligneCommandeMapper.toDtoList(lignesCommande);
    }

    @Override
    public Page<LigneCommandeDto> recuperationParPagination(int page, int limit) {

        // Vérifier si la page et la limite sont valides
        if (page < 0 || limit <= 0) {
            throw new IllegalArgumentException("La page doit être >= 0 et la limite doit être > 0.");
        }

        Pageable pageable = PageRequest.of(page, limit);
        Page<LigneCommande> pageLignesCommande = ligneCommandeRepository.findAll(pageable);
        return pageLignesCommande.map(ligneCommandeMapper::toDto);
    }

}
