package com.officePharmaceutiqueNationale.OPN.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class DciDto {

    private String id;

    @NotBlank(message = "Le nom du DCI est obligatoire")
    @Size(max = 100, message = "Le nom du DCI ne peut pas dépasser 100 caractères")
    private String nomDci;

    private Boolean isDeleted;

}
