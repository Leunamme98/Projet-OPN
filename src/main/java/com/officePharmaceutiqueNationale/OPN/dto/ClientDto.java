package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientDto {

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

    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères")
    private String motDePasse;

    private String cheminPhoto;

    @NotBlank(message = "Le nom de l'entreprise ne peut pas être vide")
    private String nomEntreprise;

    @NotBlank(message = "Le numéro d'accréditation ne peut pas être vide")
    private String numeroAccreditation;

    @NotBlank(message = "Le type de structure ne peut pas être vide")
    private String typeStructure;

    @NotBlank(message = "Le nom du responsable ne peut pas être vide")
    private String nomDuResponsable;

    @NotBlank(message = "Le numéro de contact du responsable ne peut pas être vide")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Le format du numéro de contact est invalide")
    private String numeroContactResponsable;

    private Boolean isDeleted;

    private Boolean isActive;
}
