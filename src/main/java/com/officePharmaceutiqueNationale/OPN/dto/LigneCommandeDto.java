package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Data
public class LigneCommandeDto {

    private String id;

    @Min(value = 1, message = "La quantité de la ligne de commande doit être supérieure à 0")
    private int quantiteLigneCommande;

    private Double prixLigneCommande;

    @NotNull(message = "L'article ne peut pas être nul")
    private ArticleDto article;

    @NotNull(message = "La commande ne peut pas être nulle")
    private CommandeDto commande;
}
