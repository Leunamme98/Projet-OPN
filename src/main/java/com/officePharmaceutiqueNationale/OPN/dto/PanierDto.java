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
public class PanierDto {

    private String panierId;

    // Relation avec Client
    private ClientDto client;

    // Liste des LignePanier associées
    private List<LignePanierDto> lignePaniers;
}
