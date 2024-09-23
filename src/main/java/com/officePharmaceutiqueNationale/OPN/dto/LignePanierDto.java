package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LignePanierDto {

    private String idLignePanier;
    private int quantite;

    // Relations
    private PanierDto panier;
    private ArticleDto article;
}
