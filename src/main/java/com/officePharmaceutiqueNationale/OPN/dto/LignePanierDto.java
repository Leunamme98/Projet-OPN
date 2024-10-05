package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LignePanierDto {

    private String id;
    private int quantite;

    // Relations
    private PanierDto panier;
    private ArticleDto article;
}
