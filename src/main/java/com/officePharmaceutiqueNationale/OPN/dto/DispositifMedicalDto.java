package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;


@Getter  @Setter
public class DispositifMedicalDto extends ArticleDto {

    @Size(min = 1, max = 50, message = "Le pays de fabrication doit comporter entre 1 et 50 caractères")
    private String paysFabrication;

}
