package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Classe représentant un mouvement de stock pour un produit.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MouvementStock {

    @Id
    private String id;

    /**
     * Quantité de produit modifiée dans ce mouvement.
     */
    private Integer quantite;

    /**
     * Type de mouvement (entrée, sortie, ajustement, etc.).
     */
    @Enumerated(EnumType.STRING)
    private TypeMouvement typeMouvement;

    /**
     * Date du mouvement de stock.
     */
    private LocalDate dateMouvement;

    /**
     * Référence au produit concerné par le mouvement.
     */
    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;
}
