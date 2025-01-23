package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.EtatReclamation;
import com.officePharmaceutiqueNationale.OPN.model.TypeReclamation;


import java.time.LocalDate;

public class ReclamationDto {
    private String id;
    private String descriptionReclamation;
    private LocalDate dateReclamation;
    private TypeReclamation typeReclamation;
    private EtatReclamation etatReclamation;
    private CommandeDto commande;
}
