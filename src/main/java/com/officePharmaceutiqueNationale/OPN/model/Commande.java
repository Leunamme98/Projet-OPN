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

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "commande")
    private List<Reclamation> reclamations;

    @OneToOne
    private Facture facture;

    @OneToMany(mappedBy = "commande")
    private List<Livraison> livraisons;
}
