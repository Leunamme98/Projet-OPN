package com.officePharmaceutiqueNationale.OPN.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Reclamation {

    @Id
    private String id;

    private String description;

    private LocalDate dateReclamation;

    private EtatReclamation etatReclamation;
}
