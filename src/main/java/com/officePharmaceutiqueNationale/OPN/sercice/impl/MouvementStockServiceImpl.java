package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.MouvementStockDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.MouvementStockMapper;
import com.officePharmaceutiqueNationale.OPN.model.Article;
import com.officePharmaceutiqueNationale.OPN.model.MouvementStock;
import com.officePharmaceutiqueNationale.OPN.model.TypeMouvement;
import com.officePharmaceutiqueNationale.OPN.repository.ArticleRepository;
import com.officePharmaceutiqueNationale.OPN.repository.MouvementStockRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.MouvementStockService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class MouvementStockServiceImpl implements MouvementStockService {

    private final ArticleRepository articleRepository;
    private final MouvementStockRepository mouvementStockRepository;
    private final MouvementStockMapper mouvementStockMapper;

    @Override
    public MouvementStockDto enregistrerMouvement(MouvementStockDto mouvementStockDto) {

        // Récupérer l'article en fonction de son ID
        Article article = articleRepository.findById(mouvementStockDto.getArticleDto().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Article non trouvé avec l'ID : " + mouvementStockDto.getArticleDto().getId()));

        // Créer un nouvel objet MouvementStock à partir du DTO
        MouvementStock mouvementStock = mouvementStockMapper.toEntity(mouvementStockDto);
        mouvementStock.setId(UUID.randomUUID().toString());
        mouvementStock.setDateMouvement(LocalDate.now());
        mouvementStock.setArticle(article);

        // Vérifier le type de mouvement
        if (mouvementStock.getTypeMouvement() == TypeMouvement.ENTREE) {
            // Approvisionnement: Augmenter le stock de l'article
            article.setQuantiteStock(article.getQuantiteStock() + mouvementStock.getQuantite());
        } else if (mouvementStock.getTypeMouvement() == TypeMouvement.SORTIE) {
            // Vérifier la quantité en stock avant de soustraire
            if (article.getQuantiteStock() >= mouvementStock.getQuantite()) {
                article.setQuantiteStock(article.getQuantiteStock() - mouvementStock.getQuantite());
            } else {
                throw new IllegalArgumentException("Stock insuffisant pour cet article.");
            }
        }

        // Enregistrer le mouvement de stock et mettre à jour l'article
        mouvementStockRepository.save(mouvementStock);
        articleRepository.save(article);

        // Retourner le DTO du mouvement enregistré
        return mouvementStockMapper.toDto(mouvementStock);
    }


@Override
public List<MouvementStockDto> obtenirMouvementsParArticle(String articleId) {
    // Récupérer tous les mouvements de stock associés à l'article donné
    List<MouvementStock> mouvementsStock = mouvementStockRepository.findByArticleId(articleId);

    // Utiliser la méthode toDtoList de MouvementStockMapper pour convertir la liste en DTOs
    return mouvementStockMapper.toDtoList(mouvementsStock);
}

    @Override
    public MouvementStockDto obtenirMouvementParId(String mouvementId) {
        MouvementStock mouvementStock = mouvementStockRepository.findById(mouvementId)
                .orElseThrow(() -> new ResourceNotFoundException("Mouvement de stock non trouvé avec l'ID : " + mouvementId));
        return mouvementStockMapper.toDto(mouvementStock);
    }

    @Override
    public List<MouvementStockDto> obtenirTousLesMouvements() {
        List<MouvementStock> mouvementStocks =  mouvementStockRepository.findAll();
        return mouvementStockMapper.toDtoList(mouvementStocks);
    }
}
