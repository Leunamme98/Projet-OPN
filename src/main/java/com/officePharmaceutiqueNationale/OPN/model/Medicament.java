package com.officePharmaceutiqueNationale.OPN.model;

import com.officePharmaceutiqueNationale.OPN.model.FormeGalenique;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Medicament")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicament extends Article {

    private Double concentration;
    private String uniteConcentration;

    @ManyToOne
    private FormeGalenique formeGalenique;

    @ManyToOne
    private SpecialitePharmaceutique specialitePharmaceutique;

    // Constructeur explicitement défini, appelant le constructeur de la classe parente
    public Medicament(String id, String code, String libelle, LocalDate dateExpiration,
                      Double prixGenerique, Double prixUnitaire, int quantiteStockSeuil,
                      int quantiteStock, String description, EtatArticle etat,
                      Boolean isDeleted, Double concentration, String uniteConcentration,
                      FormeGalenique formeGalenique, SpecialitePharmaceutique specialitePharmaceutique) {

        // Appel du constructeur de la classe parente (Article)
        super(id, code, libelle, dateExpiration, prixGenerique, prixUnitaire, quantiteStockSeuil,
                quantiteStock, description, null, etat, isDeleted);

        // Initialisation des propriétés spécifiques à Medicament
        this.concentration = concentration;
        this.uniteConcentration = uniteConcentration;
        this.formeGalenique = formeGalenique;
        this.specialitePharmaceutique = specialitePharmaceutique;
    }
}
