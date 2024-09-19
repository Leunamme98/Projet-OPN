package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * Classe représentant un panier d'achat pour un client.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Panier {

    /**
     * Identifiant unique du panier.
     */
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    /**
     * Référence au client qui possède le panier.
     */
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    /**
     * Ensemble des lignes du panier représentant les produits et quantités.
     */
    @OneToMany(mappedBy = "panier")
    private Set<LignePanier> lignesPanier;

}
