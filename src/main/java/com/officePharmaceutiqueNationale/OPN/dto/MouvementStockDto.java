package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.TypeMouvement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouvementStockDto {

    private String id;

    private int quantite;
    private TypeMouvement typeMouvement;
    private LocalDate dateMouvement;
    private Double prix;
    
    // Relation avec l'article
    private ArticleDto article;
}
