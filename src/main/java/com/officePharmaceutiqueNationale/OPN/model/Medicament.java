package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Medicament extends Article {

    private Double concentration;
    private String uniteConcentration;

    @ManyToOne
    private FormeGalenique formeGalenique;

    @ManyToOne
    private SpecialitePharmaceutique specialitePharmaceutique;

}
