package com.officePharmaceutiqueNationale.OPN.dto;

import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AgentOpnDto extends UtilisateurDto {

    private String matriculeAgent;

    private LocalDate dateEmbauche;
}
