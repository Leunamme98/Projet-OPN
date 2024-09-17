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
 * Classe représentant un produit pharmaceutique dans le système.
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Produit {

    /**
     * Identifiant unique du produit.
     * Doit être unique et ne peut pas être nul.
     */
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    /**
     * Nom générique du produit (par exemple, paracétamol).
     * Ce champ est obligatoire et ne peut pas dépasser 100 caractères.
     */
    @Column(nullable = false, length = 100)
    private String nomGenerique;

    /**
     * Nom commercial du produit (par exemple, Doliprane).
     * Ce champ est obligatoire et ne peut pas dépasser 100 caractères.
     */
    @Column(nullable = false, length = 100)
    private String nomCommercial;

    /**
     * Description détaillée du produit pour aider à la compréhension de son utilisation.
     * Ce champ est optionnel, mais s'il est rempli, il ne peut pas dépasser 500 caractères.
     */
    @Column(length = 500)
    private String description;

    /**
     * Prix d'achat unitaire du produit.
     * Ce champ est obligatoire et doit être un nombre positif.
     */
    @NotNull
    @Positive
    private BigDecimal prixAchatUnitaire;

    /**
     * Prix de vente unitaire du produit.
     * Ce champ est obligatoire et doit être un nombre positif.
     */
    @NotNull
    @Positive
    private BigDecimal prixVenteUnitaire;

    /**
     * Quantité de produit en stock.
     * La quantité doit être un entier supérieur ou égal à 0.
     */
    @NotNull
    @Min(0)
    private Integer quantiteStock;

    /**
     * Quantité seuil pour déclencher une alerte de réapprovisionnement.
     * Ce champ est obligatoire et doit être un entier supérieur ou égal à 0.
     */
    @NotNull
    @Min(0)
    private Integer quantiteSeuil;

    /**
     * Date d'expiration du produit.
     * Ce champ est obligatoire et doit représenter une date future.
     */
    @NotNull
    private LocalDate dateExpiration;

    /**
     * Chemin vers l'image du produit.
     * Ce champ est optionnel et ne peut pas dépasser 255 caractères.
     */
    @Column(length = 255)
    private String imageProduit;
}
