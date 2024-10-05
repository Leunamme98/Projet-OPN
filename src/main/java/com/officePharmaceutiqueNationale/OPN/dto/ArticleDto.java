package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class ArticleDto {

    private String id;

    private String code;
    private String libelle;
    private LocalDate dateExpiration;
    private Double prixGenerique;
    private int quantiteStockSeuil;
    private String description;
    private EtatArticle etat;
}
