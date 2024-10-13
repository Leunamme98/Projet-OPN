package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatCommande;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class CommandeDto {

    private String id;
    private Double montantTotalCommande;

    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    private ClientDto client;
    private FactureDto facture;

}
