package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    @NotBlank
    private String id;

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    @Size(min = 3, max = 50)
    private String identifiant;

    @NotBlank(message = "Le nom complet ne peut pas être vide")
    private String nomComplet;

    private String adresse;

    private String numeroTelephone;

    @Email(message = "Email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String motDePasse;

    private String cheminPhoto;
}
