package com.officePharmaceutiqueNationale.OPN.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Utilisateur {

    @Id
    private String id;

    private String nomUtilisateur;

    private String email;

    private String password;

    private String telephone;

    private String adresse;

    private boolean estOperationnel;

    private String photo;
}
