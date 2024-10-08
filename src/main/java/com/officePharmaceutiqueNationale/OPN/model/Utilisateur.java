package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Utilisateur_type")
@Data
public abstract class Utilisateur {

    @Id
    private String id;

    private String identifiant;

    private String nomComplet;

    private String adresse;

    private String numeroTelephone;

    @Email(message = "Email invalide")
    private String email;

    private String motDePasse;

    private String cheminPhoto;

    private String urlPhoto;
}
