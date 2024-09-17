package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Commande {

    @Id
    private String id;
    private BigDecimal montantCommande;
    private EtatCommande etatCommande;

}
