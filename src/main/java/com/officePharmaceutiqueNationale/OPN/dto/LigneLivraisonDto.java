package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneLivraisonDto {

    private String id;
    private int quantiteLivraison;

    // Relations
    private LivraisonDto livraison;
    private ArticleDto article;
}
