package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import jakarta.persistence.ManyToMany;

/**
 * Représente un rôle dans le système.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    // Identifiant unique du rôle
    private String id;

    // Nom du rôle (ex. ADMIN, USER)
    private String nomRole;

    private String description; // Description du rôle

    @ManyToMany(mappedBy = "roles")
    private Set<AgentOPN> agents; // Agents associés à ce rôle
}
