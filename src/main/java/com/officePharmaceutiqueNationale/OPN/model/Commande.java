package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Commande {

    @Id
    private String id;

    private Double montantTotalCommande;

    private EtatCommande etatCommande;


    @ManyToOne
    private Client client;

    @OneToOne
    private Facture facture;

}
