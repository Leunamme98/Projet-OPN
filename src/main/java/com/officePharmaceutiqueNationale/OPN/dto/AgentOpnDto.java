package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class AgentOpnDto extends UtilisateurDto {

    @NotBlank(message = "Le matricule de l'agent ne peut pas être vide")
    private String matriculeAgent;

    @NotNull(message = "La date d'embauche ne peut pas être nulle")
    private LocalDate dateEmbauche;
}
