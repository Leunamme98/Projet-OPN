package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class LignePanier {

    @Id
    private String id;

    private int quantite;

    @ManyToOne
    private Panier panier;

    @ManyToOne
    private Article article;
}
