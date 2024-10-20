package com.officePharmaceutiqueNationale.OPN.dto;


import lombok.Data;

@Data
public class PanierDto {

    private String id;
    private Double prixTotalPanier;
    private int nombreArticle;
    private String clientId;

}
