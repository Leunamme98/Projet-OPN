package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("Dispositif Médical")
@Getter @Setter
public class DispositifMedical extends Article {
    private String paysFabrication;
}
