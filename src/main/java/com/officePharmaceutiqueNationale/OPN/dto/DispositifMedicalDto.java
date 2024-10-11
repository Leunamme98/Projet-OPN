package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatArticle;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DispositifMedicalDto {

    private String id;

    @NotNull(message = "Le code ne peut pas être nul")
    @Size(min = 1, max = 50, message = "Le code doit comporter entre 1 et 50 caractères")
    private String code;

    @NotNull(message = "Le libellé ne peut pas être nul")
    @Size(min = 1, max = 100, message = "Le libellé doit comporter entre 1 et 100 caractères")
    private String libelle;

    @Future(message = "La date d'expiration doit être dans le futur")
    private LocalDate dateExpiration;

    @NotNull(message = "Le prix générique ne peut pas être nul")
    @Min(value = 0, message = "Le prix générique doit être non négatif")
    private Double prixGenerique;

    @NotNull(message = "La quantité de stock seuil ne peut pas être nulle")
    @Min(value = 0, message = "La quantité de stock seuil doit être non négative")
    private int quantiteStockSeuil;

    @NotNull(message = "La quantité de stock ne peut pas être nulle")
    @Min(value = 0, message = "La quantité de stock doit être non négative")
    private int quantiteStock;

    private String description;

    private String cheminImage;

    private Boolean isDeleted;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "L'état ne peut pas être nul")
    private EtatArticle etat;

    @Size(min = 1, max = 50, message = "Le pays de fabrication doit comporter entre 1 et 50 caractères")
    private String paysFabrication;
}
