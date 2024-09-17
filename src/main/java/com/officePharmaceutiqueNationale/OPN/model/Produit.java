package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Représente un produit pharmaceutique.
 * Les produits peuvent être génériques ou spécifiques, avec des informations de stock et de vente.
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Produit {

    @Id
    @Column(nullable = false, unique = true)
    private String id;

    /**
     * Nom générique du produit (par exemple, paracétamol).
     */
    @Column(nullable = false, length = 100)
    private String nomGenerique;

    /**
     * Nom commercial du produit (par exemple, Doliprane).
     */
    @Column(nullable = false, length = 100)
    private String nomCommercial;

    /**
     * Description détaillée du produit, utilisée pour informer les utilisateurs ou clients.
     */
    @Column(length = 500)
    private String description;

    /**
     * Prix d'achat unitaire du produit. Ne peut pas être nul ou négatif.
     */
    @NotNull
    @Positive
    private BigDecimal prixAchatUnitaire;

    /**
     * Prix de vente unitaire du produit. Doit être supérieur à 0.
     */
    @NotNull
    @Positive
    private BigDecimal prixVenteUnitaire;

    /**
     * Quantité en stock.
     */
    @NotNull
    @Min(0)
    private Integer quantiteStock;

    /**
     * Quantité seuil pour alerter lorsqu'il faut réapprovisionner.
     */
    @NotNull
    @Min(0)
    private Integer quantiteSeuil;

    /**
     * Date d'expiration du produit. Doit être future.
     */
    @NotNull
    private LocalDate dateExpiration;

    /**
     * Chemin vers l'image du produit.
     */
    @Column(length = 255)
    private String imageProduit;
}
