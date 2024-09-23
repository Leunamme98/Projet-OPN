package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactureDto {

    private String idFacture;
    private LocalDate dateFacture;
    private String cheminPdfFacture;
    private CommandeDto commande;
}
