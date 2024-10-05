package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatReclamation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReclamationDto {

    private String id;
    private String descriptionReclamation;
    private LocalDate dateReclamation;
    private EtatReclamation etatReclamation;

    // Relation avec Commande
    private CommandeDto commande;
}
