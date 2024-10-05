package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatLivraison;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonDto {

    private String id;
    private LocalDate dateLivraison;
    private EtatLivraison etatLivraison;

    // Relations
    private CommandeDto commande;
    private List<LigneLivraisonDto> ligneLivraisons;
}
