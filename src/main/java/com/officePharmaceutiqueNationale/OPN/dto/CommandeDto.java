package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatCommande;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto {

    @NotBlank(message = "L'ID de la commande ne peut pas être vide")
    private String idCommande;

    @NotNull(message = "La quantité totale de commande ne peut pas être nulle")
    private Integer quantiteTotaleCommande;

    @NotNull(message = "L'état de la commande ne peut pas être nul")
    private EtatCommande etatCommande;

    private List<LigneCommandeDto> ligneCommandes;

    private ClientDto client;

    private List<ReclamationDto> reclamations;

    private FactureDto facture;

    private List<LivraisonDto> livraisons;
}
