package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;



@Entity
@DiscriminatorValue("Client")
@Getter @Setter
public class Client extends Utilisateur {

    private String nomEntreprise;
    private String numeroAccreditation;
    private String typeStructure;
    private String nomDuResponsable;
    private String numeroContactResponsable;

}
