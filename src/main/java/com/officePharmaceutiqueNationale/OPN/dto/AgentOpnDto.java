package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AgentOpnDto {

    private String id;

    @NotBlank(message = "L'identifiant ne peut pas être vide")
    private String identifiant;

    @NotBlank(message = "L'adresse ne peut pas être vide")
    private String adresse;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Le format du numéro de téléphone est invalide")
    private String numeroTelephone;

    @Email(message = "L'email est invalide")
    @NotBlank(message = "L'email ne peut pas être vide")
    private String email;

    private String motDePasse;

    private String cheminPhoto;

    @NotBlank(message = "Le matricule ne peut pas être vide")
    private String matriculeAgent;

    @NotBlank(message = "Le nom de l'agent ne peut pas être vide")
    private String nomAgent;

    @NotBlank(message = "Le prénom de l'agent ne peut pas être vide")
    private String prenomAgent;

    private Boolean isDeleted;

    private Boolean isActive;
}
