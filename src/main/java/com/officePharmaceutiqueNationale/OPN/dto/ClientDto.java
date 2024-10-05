package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends UtilisateurDto {

    @NotBlank(message = "Le nom de l'entreprise ne peut pas être vide")
    private String nomEntreprise;

    @NotBlank(message = "Le numéro d'accréditation ne peut pas être vide")
    private String numeroAccreditation;

    @NotBlank(message = "Le type de structure ne peut pas être vide")
    private String typeStructure;

    @NotBlank(message = "Le nom du responsable ne peut pas être vide")
    private String nomDuResponsable;

    @NotBlank(message = "Le numéro de contact du responsable ne peut pas être vide")
    private String numeroContactResponsable;

    private List<PanierDto> paniers;

    private List<CommandeDto> commandes;
}
