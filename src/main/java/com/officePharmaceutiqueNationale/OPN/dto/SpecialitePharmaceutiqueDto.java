package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialitePharmaceutiqueDto {

    private String idSpecialiteMedicament;
    private String libelleSpecialiteMedicament;
    private String nomLaboratoire;

    // Liste des médicaments associés
    private List<MedicamentDto> medicaments;

    // Relation avec Dci
    private DciDto dci;
}
