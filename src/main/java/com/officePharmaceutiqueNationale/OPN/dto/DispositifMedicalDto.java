package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DispositifMedicalDto {

    private String id;

    private String code;
    private String libelle;
    private LocalDate dateExpiration;
    private Double prixGenerique;
    private int quantiteStockSeuil;
    private String description;
    private String etat;

    private String paysFabrication;

}
