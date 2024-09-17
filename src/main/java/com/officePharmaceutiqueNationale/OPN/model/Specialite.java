package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Classe représentant une spécialité pharmaceutique dans le système.
 *
 * Une spécialité est un produit pharmaceutique spécifique, identifié par
 * un nom de spécialité, une dénomination commune internationale (DCI), et une description.
 */
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Specialite {

    /**
     * Identifiant unique de la spécialité.
     * Ce champ est utilisé comme clé primaire.
     */
    @Id
    private String id;

    /**
     * Nom de la spécialité.
     * Par exemple, "Aspirine", "Ibuprofène".
     */
    private String nomSpecialite;

    /**
     * Dénomination Commune Internationale (DCI) de la spécialité.
     * Nom générique du principe actif de la spécialité.
     */
    private String DCI;

    /**
     * Description de la spécialité.
     * Détails supplémentaires ou caractéristiques de la spécialité.
     */
    private String description;

    /**
     * Liste des médicaments associés à cette spécialité.
     * Une spécialité peut regrouper plusieurs médicaments.
     */
    @OneToMany(mappedBy = "specialite")
    private Set<Medicament> medicaments;

}
