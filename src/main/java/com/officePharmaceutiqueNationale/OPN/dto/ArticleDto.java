package com.officePharmaceutiqueNationale.OPN.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {

    private String id;

    private String code;

    private String libelle;

    private LocalDate dateExpiration;

    private Double prixDeVente;

    private int quantiteStock;

    private String description;

    private List<LignePanierDto> lignePaniers;

    private List<MouvementStockDto> mouvementStocks;

    private List<LigneCommandeDto> ligneCommandes;

    private List<LigneLivraisonDto> ligneLivraisons;
}
