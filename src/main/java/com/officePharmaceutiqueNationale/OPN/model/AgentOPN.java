package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

/**
 * Représente un agent de l'OPN dans le système.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentOPN extends Utilisateur {

    // Matricule de l'agent
    private String Matricule;

    // Nom de l'agent
    private String nom;

    // Prénom de l'agent
    private String prenom;

    // Date de naissance de l'agent
    private LocalDate dateNaissance;

    // Date d'embauche de l'agent
    private LocalDate dateEmbauche;

    @ManyToMany
    @JoinTable(
            name = "agent_role",
            joinColumns = @JoinColumn(name = "agent_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    // Rôles associés à l'agent
    private Set<Role> roles;
}
