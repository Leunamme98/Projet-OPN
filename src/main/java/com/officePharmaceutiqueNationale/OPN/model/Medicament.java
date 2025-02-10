package com.officePharmaceutiqueNationale.OPN.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Medicament")
@Getter @Setter
public class Medicament extends Article {
    private Double concentration;
    private String uniteConcentration;

    @ManyToOne
    private FormeGalenique formeGalenique;

    @ManyToOne
    private SpecialitePharmaceutique specialitePharmaceutique;
}
