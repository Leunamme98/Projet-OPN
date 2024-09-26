package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Article {

    @Id
    private String id;

    private String code;
    private String Libelle;
    private LocalDate dateExpiration;
    private Double prixGenerique;
    private int quantiteStock;
    private String description;

    @OneToMany(mappedBy = "article")
    private List<LignePanier> lignePaniers;

    @OneToMany(mappedBy = "article")
    private List<MouvementStock> mouvementStocks;

    @OneToMany(mappedBy = "article")
    private List<LigneCommande> ligneCommandes;

    @OneToMany(mappedBy = "article")
    private List<LigneLivraison> ligneLivraisons;

}
