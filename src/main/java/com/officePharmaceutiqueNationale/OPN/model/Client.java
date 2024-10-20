package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@DiscriminatorValue("Client")
@Getter @Setter
public class Client extends Utilisateur {

    private String nomEntreprise;
    private String numeroAccreditation;
    private String typeStructure;
    private String nomDuResponsable;
    private String numeroContactResponsable;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Panier panier;

}
