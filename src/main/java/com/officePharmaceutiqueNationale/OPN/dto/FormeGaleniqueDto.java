package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FormeGaleniqueDto {

    private String id;

    @NotBlank(message = "Le nom de la forme galénique est obligatoire")
    @Size(max = 100, message = "Le nom de la forme galénique ne doit pas dépasser 100 caractères")
    private String nomFormeGalenique;

    @Size(max = 1000, message = "La description de la forme galénique ne doit pas dépasser 1000 caractères")
    private String descriptionFormeGalenique;

    private Boolean isDeleted;
}
