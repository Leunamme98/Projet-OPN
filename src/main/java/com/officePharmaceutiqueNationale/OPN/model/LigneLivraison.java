package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class LigneLivraison {

    @Id
    private String id;

    private int quantiteLivraison;

    @ManyToOne
    private Livraison livraison;

    @ManyToOne
    private Article article;
}
