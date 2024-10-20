package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatArticle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ArticleDto {

    private String id;

    @NotBlank(message = "Le code de l'article ne peut pas être vide")
    private String code;

    @NotBlank(message = "Le libellé de l'article ne peut pas être vide")
    private String libelle;

    @FutureOrPresent(message = "La date d'expiration doit être dans le futur ou aujourd'hui")
    private LocalDate dateExpiration;

    @DecimalMin(value = "0.0", inclusive = true, message = "Le prix générique ne peut pas être négatif")
    private Double prixGenerique;

    @DecimalMin(value = "0.0", inclusive = true, message = "Le prix unitaire ne peut pas être négatif")
    private Double prixUnitaire;

    @Min(value = 10, message = "La quantité seuil doit être positive ou nulle")
    private int quantiteStockSeuil;

    @Min(value = 0, message = "La quantité en stock doit être positive ou nulle")
    private int quantiteStock;

    @Size(max = 255, message = "La description ne peut pas dépasser 255 caractères")
    private String description;

    private String cheminImage;

    @NotNull(message = "L'état de l'article est requis")
    @Enumerated(EnumType.STRING)
    private EtatArticle etat;

    private Boolean isDeleted;
}