package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class CommandeDto {

    private String id;

    private Double montantTotalCommande;

    private EtatCommande etatCommande;

    private List<LigneCommandeDto> ligneCommandes;

    private ClientDto client;

    private List<ReclamationDto> reclamations;

    private FactureDto facture;

    private List<LivraisonDto> livraisons;
}
