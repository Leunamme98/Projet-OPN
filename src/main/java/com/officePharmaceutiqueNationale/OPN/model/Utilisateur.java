package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Utilisateur_type")
@Data
public class Utilisateur {

    @Id
    private String id;

    private String identifiant;

    private String adresse;

    private String numeroTelephone;

    private String email;

    private String motDePasseHache;

    private String cheminPhoto;

    private Boolean isDeleted;

    private Boolean isActive;
}
