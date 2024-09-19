package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Classe représentant une ligne dans le panier d'achat.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LignePanier {

    /**
     * Identifiant unique de la ligne du panier.
     */
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    /**
     * Référence au produit ajouté dans le panier.
     */
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    /**
     * Quantité de produit dans le panier.
     */
    @Column(nullable = false)
    private Integer quantite;


    /**
     * Référence au panier auquel cette ligne appartient.
     */
    @ManyToOne
    @JoinColumn(name = "panier_id", nullable = false)
    private Panier panier;
}
