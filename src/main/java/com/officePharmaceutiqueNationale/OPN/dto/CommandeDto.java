package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.enums.EtatCommande;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommandeDto {

    private String id;
    private String numeroCommande;
    private Double montantTotalCommande;
    LocalDateTime dateCommande;

    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    private ClientDto client;

    //private FactureDto facture;

}
