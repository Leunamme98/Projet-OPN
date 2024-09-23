package com.officePharmaceutiqueNationale.OPN.dto;

import java.util.List;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ClientDto extends UtilisateurDto {

    private String nomEntreprise;

    private String numeroAccreditation;

    private String typeStructure;

    private String nomDuResponsable;

    private String numeroContactResponsable;

    private List<PanierDTO> paniers;

    private List<CommandeDTO> commandes;
}
