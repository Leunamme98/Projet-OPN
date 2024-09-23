package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.TypeMouvement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MouvementStockDto {

    private String idMouvementStock;
    private int quantite;
    private TypeMouvement typeMouvement;
    private LocalDate dateMouvement;

    // Relation avec l'article
    private ArticleDto article;
}
