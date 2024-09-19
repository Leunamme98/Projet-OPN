package com.officePharmaceutiqueNationale.OPN.model;

/**
 * Enumération représentant les différents états d'une commande.
 */
public enum EtatCommande {
    /**
     * La commande a été passée et est en attente de traitement.
     */
    EN_ATTENTE,

    /**
     * La commande a été envoyée au client mais n'a pas encore été reçue.
     */
    ENVOYE,

    /**
     * La commande a été reçue par le client.
     */
    RECU,

    /**
     * La commande est en cours de traitement pour la préparation et l'expédition.
     */
    EN_COURS,

    /**
     * La commande a été entièrement traitée et clôturée.
     */
    TRAITE,

    /**
     * La commande a été annulée avant ou après l'expédition.
     */
    ANNULEE,

    /**
     * La commande est en attente de retour ou de remboursement.
     */
    EN_ATTENTE_RETROU

}
