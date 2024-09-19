package com.officePharmaceutiqueNationale.OPN.model;

/**
 * Enumération représentant les différents états d'une réclamation.
 */
public enum EtatReclamation {
    /**
     * La réclamation a été reçue et est en attente de traitement.
     */
    RECU,

    /**
     * La réclamation est actuellement en cours de traitement par le service concerné.
     */
    EN_COURS,

    /**
     * La réclamation a été traitée et une réponse ou une action a été fournie.
     */
    TRAITE,

    /**
     * La réclamation a été rejetée, souvent en raison de non-conformité ou d'inadmissibilité.
     */
    REJETEE,

    /**
     * La réclamation a été résolue mais attend confirmation ou validation finale.
     */
    EN_ATTENTE_CONFIRMATION,

    /**
     * La réclamation a été fermée, signifiant qu'aucune autre action ne sera prise.
     */
    FERME
}
