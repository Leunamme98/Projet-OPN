package com.officePharmaceutiqueNationale.OPN.dto;


import com.officePharmaceutiqueNationale.OPN.model.Panier;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;


public class LignePanierDto {

    private String id;

    @Min(value = 1, message = "La quantité de la ligne   doit être supérieure à 0")
    private int quantite;

    @NotNull(message = "L'article ne peut pas être nul")
    private Panier panier;


    private ArticleDto articleDto;
}
