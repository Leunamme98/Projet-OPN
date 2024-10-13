package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatArticle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleDto {

    private String id;

    private String code;
    private String libelle;
    private LocalDate dateExpiration;
    private Double prixGenerique;
    private int quantiteStockSeuil;
    private int quantiteStock;
    private String description;
    private String cheminImage;

    @Enumerated(EnumType.STRING)
    private EtatArticle etat;

    private Boolean isDeleted;

}
