package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe de base pour tous les utilisateurs.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    // Identifiant unique de l'utilisateur
    private String id;

    // Nom d'utilisateur pour la connexion
    private String nomUtilisateur;

    // Adresse email de l'utilisateur
    private String email;

    // Mot de passe (crypté)
    private String password;

    // Numéro de téléphone
    private String telephone;

    // Adresse physique
    private String adresse;

    // Indicateur si l'utilisateur est actif ou non
    private boolean estOperationnel;

    // Chemin vers la photo de profil
    private String photo;
}
