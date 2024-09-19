package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe représentant une facture dans le système.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture {

    @Id
    private String id;

    /**
     * Date à laquelle la facture a été émise.
     */
    @Column(nullable = false)
    private LocalDate dateFacture;

    /**
     * Montant total de la facture (hors taxe).
     */
    @Column(nullable = false)
    private BigDecimal montantTotal;

    /**
     * Montant de la TVA appliquée.
     */
    @Column(nullable = false)
    private BigDecimal montantTVA;

    /**
     * Montant total TTC (toutes taxes comprises).
     */
    @Column(nullable = false)
    private BigDecimal montantTotalTTC;

    /**
     * Conditions de paiement spécifiées pour la facture.
     */
    @Column(length = 255)
    private String conditionsPaiement;

    /**
     * Statut de paiement de la facture.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutPaiement statutPaiement;

    /**
     * Mode de paiement utilisé pour régler la facture.
     */
    @Column(length = 100)
    private String modePaiement;


    /**
     * Adresse de facturation.
     */
    @Column(length = 255)
    private String adresseFacturation;

    /**
     * Référence à la commande associée à la facture.
     */
    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;
}
