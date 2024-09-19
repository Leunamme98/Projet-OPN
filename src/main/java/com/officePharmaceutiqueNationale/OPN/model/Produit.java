package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Représente un produit pharmaceutique.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Produit {

    /**
     * Identifiant unique du produit.
     */
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    /**
     * Nom générique du produit (ex. paracétamol).
     */
    @NotBlank(message = "Le nom générique est obligatoire")
    @Column(nullable = false, length = 100)
    private String nomGenerique;

    /**
     * Nom commercial du produit (ex. Doliprane).
     */
    @NotBlank(message = "Le nom commercial est obligatoire")
    @Column(nullable = false, length = 100)
    private String nomCommercial;

    /**
     * Description détaillée du produit.
     */
    @Size(max = 500, message = "La description ne doit pas dépasser 500 caractères")
    private String description;

    /**
     * Prix d'achat unitaire.
     */
    @NotNull(message = "Le prix d'achat est obligatoire")
    @Positive(message = "Le prix d'achat doit être positif")
    private BigDecimal prixAchatUnitaire;

    /**
     * Prix de vente unitaire.
     */
    @NotNull(message = "Le prix de vente est obligatoire")
    @Positive(message = "Le prix de vente doit être positif")
    private BigDecimal prixVenteUnitaire;

    /**
     * Quantité en stock.
     */
    @NotNull
    @Min(value = 0, message = "La quantité en stock doit être supérieure ou égale à 0")
    private Integer quantiteStock;

    /**
     * Quantité seuil pour le réapprovisionnement.
     */
    @NotNull
    @Min(value = 0, message = "La quantité seuil doit être supérieure ou égale à 0")
    private Integer quantiteSeuil;

    /**
     * Date d'expiration du produit.
     */
    @NotNull(message = "La date d'expiration est obligatoire")
    @Future(message = "La date d'expiration doit être dans le futur")
    private LocalDate dateExpiration;

    /**
     * Chemin de l'image du produit.
     */
    @Size(max = 255, message = "Le chemin de l'image ne peut pas dépasser 255 caractères")
    private String imageProduit;

    /**
     * Lignes de commande associées à ce produit.
     */
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LigneCommande> lignesCommande;

    /**
     * Ensemble des mouvements de stock associés au produit.
     */
    @OneToMany(mappedBy = "produit")
    private Set<MouvementStock> mouvementsStock;

}
