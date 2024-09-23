package com.officePharmaceutiqueNationale.OPN.dto;

import java.util.List;

import com.officePharmaceutiqueNationale.OPN.model.EtatCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommandeDto {

    private String idCommande;

    private int quantiteTotaleCommande;

    private EtatCommande etatCommande;

    private List<LigneCommandeDto> ligneCommandes;

    private ClientDto client;

    private List<ReclamationDto> reclamations;

    private FactureDto facture;

    private List<LivraisonDto> livraisons;
}
