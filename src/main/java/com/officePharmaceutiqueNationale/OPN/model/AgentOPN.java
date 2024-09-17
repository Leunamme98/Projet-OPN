package com.officePharmaceutiqueNationale.OPN.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class AgentOPN extends  Utilisateur{

    private String Matricule;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private LocalDate dateEmbauche;

    private BigDecimal salaire;

}
