package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FactureDto {

    private String id;
    private LocalDate dateFacture;
    private String cheminPdfFacture;

    private CommandeDto commande;

}
