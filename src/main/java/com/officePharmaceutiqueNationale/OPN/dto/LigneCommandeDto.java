package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeDto {

    private String idLigneCommande;
    private int quantiteLigneCommande;
    private Double prixLigneCommande;

    private ArticleDto article;
    private CommandeDto commande;
}
