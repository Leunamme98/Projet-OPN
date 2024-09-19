package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Représente une commande dans le système.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commande {

    /**
     * Identifiant unique de la commande.
     */
    @Id
    private String id;

    /**
     * Date à laquelle la commande a été passée.
     */
    private LocalDate dateCommande;

    /**
     * Montant total de la commande.
     */
    private BigDecimal montantCommande;

    /**
     * État actuel de la commande.
     */
    @Enumerated(EnumType.STRING)
    private EtatCommande etatCommande;

    /**
     * Client ayant passé la commande.
     */
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    /**
     * Lignes de commande associées à cette commande.
     */
    @OneToMany(mappedBy = "commande")
    private Set<LigneCommande> lignesCommande;

    /**
     * Facture associée à cette commande.
     */
    @OneToOne(mappedBy = "commande", cascade = CascadeType.ALL)
    private Facture facture;

    /**
     * Liste des livraisons associées à cette commande.
     * Une commande peut être livrée en plusieurs fois.
     */
    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private Set<Livraison> livraisons;
}
