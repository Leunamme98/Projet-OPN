package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un médicament, qui est un type spécifique de produit.
 * Hérite des propriétés de la classe Produit.
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Medicament extends Produit {

    /**
     * Dosage du médicament pour cette forme galénique.
     */
    private Float dosage;

    /**
     * Unité de mesure du dosage (e.g., mg, ml).
     */
    private String unite;


    /**
     * Forme galénique associée à ce médicament.
     * Relation plusieurs-à-un avec l'entité FormeGalenique.
     */
    @ManyToOne
    @JoinColumn(name = "forme_galenique_id")
    @NotNull(message = "La forme galénique ne peut pas être nulle.")
    private FormeGalenique formeGalenique;

    /**
     * Spécialité à laquelle appartient ce médicament.
     * Une spécialité peut regrouper plusieurs médicaments.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;
}
