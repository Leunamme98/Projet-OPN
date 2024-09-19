package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Représente une ligne de commande dans le système.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande {

    /**
     * Identifiant unique de la ligne de commande.
     */
    @Id
    private String id;

    /**
     * Quantité de produit commandée.
     */
    @Column(nullable = false)
    private Integer quantite;

    /**
     * Prix unitaire du produit lors de la commande.
     */
    @Column(nullable = false)
    private BigDecimal prixUnitaire;

    /**
     * Commande associée à cette ligne de commande.
     */
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    /**
     * Produit commandé dans cette ligne.
     */
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

}
