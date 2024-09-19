package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Représente une ligne de livraison dans le système.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneLivraison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Quantité de produit livrée.
     */
    @Column(nullable = false)
    private Integer quantite;


    /**
     * Référence à la livraison parent.
     */
    @ManyToOne
    @JoinColumn(name = "livraison_id", nullable = false)
    private Livraison livraison;

    /**
     * Référence au produit livré.
     */
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
}
