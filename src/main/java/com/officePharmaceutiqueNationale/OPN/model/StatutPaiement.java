package com.officePharmaceutiqueNationale.OPN.model;

/**
 * Enumération représentant les différents statuts de paiement d'une facture.
 */
public enum StatutPaiement {
    /**
     * La facture a été entièrement payée.
     */
    PAYEE,

    /**
     * Le paiement de la facture est en attente.
     * Cela peut signifier que le paiement a été initié mais pas encore complété.
     */
    EN_ATTENTE,

    /**
     * La facture a été partiellement payée.
     * Une partie du montant total a été réglée, mais le solde reste dû.
     */
    PARTIELLEMENT_PAYEE,

    /**
     * Le paiement de la facture a échoué pour une raison quelconque.
     * Cela peut être dû à un problème avec le mode de paiement ou un autre problème.
     */
    ECHEC,

    /**
     * La facture a été annulée et n'est plus valide.
     * Aucun paiement n'est requis.
     */
    ANNULEE
}
