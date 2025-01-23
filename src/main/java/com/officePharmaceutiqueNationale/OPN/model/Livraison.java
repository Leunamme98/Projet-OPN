package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Livraison {

    @Id
    private String id;

    private LocalDate dateLivraison;
    private EtatLivraison etatLivraison;

    @ManyToOne
    @JoinColumn(name="commande_id")
    private Commande commande;

}
