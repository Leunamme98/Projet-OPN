package com.officePharmaceutiqueNationale.OPN.model;

/**
 * Enumération représentant les différents statuts d'une livraison.
 */
public enum StatutLivraison {
    /**
     * La livraison a été créée mais pas encore traitée.
     */
    EN_ATTENTE,

    /**
     * La livraison est en cours de traitement.
     */
    EN_COURS,

    /**
     * La livraison a été effectuée avec succès.
     */
    LIVREE,

    /**
     * La livraison a échoué pour une raison quelconque.
     */
    ECHEC,

    /**
     * La livraison a été annulée.
     */
    ANNULEE
}
