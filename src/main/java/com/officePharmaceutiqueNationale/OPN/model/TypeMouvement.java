package com.officePharmaceutiqueNationale.OPN.model;

/**
 * Enumération représentant les différents types de mouvements de stock.
 */
public enum TypeMouvement {
    /**
     * Représente un mouvement d'entrée de stock, généralement lié à une réception de produit ou une augmentation de stock.
     */
    ENTREE,

    /**
     * Représente un mouvement de sortie de stock, généralement lié à une vente ou une diminution de stock.
     */
    SORTIE,

    /**
     * Représente un mouvement de stock pour un ajustement, tel qu'une correction de stock ou une mise à jour.
     */
    AJUSTEMENT,

    /**
     * Représente un mouvement de stock lié à un retour de produit, généralement lorsqu'un produit est renvoyé pour diverses raisons.
     */
    RETOUR,

    /**
     * Représente un mouvement de stock pour une perte ou un gaspillage de produit, généralement lorsque le produit est endommagé ou périmé.
     */
    PERTE
}
