package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * Représente une livraison dans le système.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livraison {

    @Id
    private String id;

    /**
     * Date à laquelle la livraison a été effectuée.
     */
    private LocalDate dateLivraison;

    /**
     * Adresse de livraison.
     */
    @Column(nullable = false, length = 255)
    private String adresseLivraison;

    /**
     * Statut de la livraison.
     */
    @Enumerated(EnumType.STRING)
    private StatutLivraison statutLivraison;

    /**
     * Référence à la commande associée à cette livraison.
     */
    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    /**
     * Liste des lignes de livraison associées à cette livraison.
     */
    @OneToMany(mappedBy = "livraison", cascade = CascadeType.ALL)
    private Set<LigneLivraison> lignesLivraison;
}
