package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.Data;

@Data
public class LigneCommandeDto {

    private String id;
    private int quantiteLigneCommande;
    private ArticleDto article;
    private CommandeDto commande;

}
