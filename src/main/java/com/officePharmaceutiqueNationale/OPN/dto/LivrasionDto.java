package com.officePharmaceutiqueNationale.OPN.dto;
import com.officePharmaceutiqueNationale.OPN.model.EtatLivraison;
import java.time.LocalDate;

public class LivrasionDto {
    private String id;

    private LocalDate dateLivraison;
    private EtatLivraison etatLivraison;
    private CommandeDto commande;
}
