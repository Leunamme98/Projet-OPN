package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class UtilisateurDto {

    private String id;

    @NotBlank(message = "L'identifiant ne peut pas être vide")
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
