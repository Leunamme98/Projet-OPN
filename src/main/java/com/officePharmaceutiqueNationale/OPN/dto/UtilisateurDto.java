package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    @NotBlank
    private String id;

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    @Size(min = 3, max = 50)
    private String identifiant;

    @NotBlank(message = "Le nom complet ne peut pas être vide")
    private String nomComplet;

    private String adresse;

    @Pattern(regexp = "\\d{10}", message = "Numéro de téléphone invalide")
    private String numeroTelephone;

    @Email(message = "Email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String motDePasse;

    private String cheminPhoto;
}
