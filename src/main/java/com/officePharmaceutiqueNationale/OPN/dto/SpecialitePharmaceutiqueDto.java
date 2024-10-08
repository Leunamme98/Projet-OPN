package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SpecialitePharmaceutiqueDto {

    private String id;

    @NotBlank(message = "Le libellé de la spécialité médicamenteuse ne peut pas être vide")
    private String libelleSpecialiteMedicament;

    @NotBlank(message = "Le nom du laboratoire ne peut pas être vide")
    private String nomLaboratoire;

    private Boolean isDeleted;

    private DciDto dci;
}
