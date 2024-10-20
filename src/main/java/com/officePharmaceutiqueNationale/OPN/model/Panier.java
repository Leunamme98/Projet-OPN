package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Panier {
    @Id
    private String id;

    private Double prixTotalPanier;
    private int nombreArticle;
    private String clientId;

}
