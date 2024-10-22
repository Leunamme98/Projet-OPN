package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Reclamation {

    @Id
    private String id;

    private String descriptionReclamation;
    private LocalDate dateReclamation;

    @Enumerated(EnumType.STRING)
    private TypeReclamation typeReclamation;

    private EtatReclamation etatReclamation;

    @ManyToOne
    private Commande commande;
}
