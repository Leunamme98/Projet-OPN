package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

}
