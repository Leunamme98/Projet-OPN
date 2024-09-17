package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Classe représentant une forme galénique dans le système.
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class FormeGalenique {

    /**
     * Identifiant unique de la forme galénique.
     */
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    /**
     * Nom de la forme galénique (par exemple, "Comprimé", "Sirop").
     * Ce champ est obligatoire.
     */
    @NotBlank(message = "Le nom de la forme galénique ne peut pas être vide.")
    private String nom;

    /**
     * Liste des médicaments associés à cette forme galénique.
     * Relation un-à-plusieurs avec l'entité Medicament.
     */
    @OneToMany(mappedBy = "formeGalenique")
    private Set<Medicament> medicaments;
}
