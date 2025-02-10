package com.officePharmaceutiqueNationale.OPN.dto;


import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class MedicamentDto extends ArticleDto {

    @NotNull(message = "La concentration ne peut pas être nulle")
    @Min(value = 0, message = "La concentration doit être non négative")
    private Double concentration;

    @NotNull(message = "L'unité de concentration ne peut pas être nulle")
    @Size(min = 1, max = 20, message = "L'unité de concentration doit comporter entre 1 et 20 caractères")
    private String uniteConcentration;

    private FormeGaleniqueDto formeGalenique;
    private SpecialitePharmaceutiqueDto specialitePharmaceutique;

}
