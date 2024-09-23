package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Client extends Utilisateur {

    private String nomEntreprise;
    private String numeroAccreditation;
    private String typeStructure;
    private String nomDuResponsable;
    private String numeroContactResponsable;

    @OneToMany(mappedBy = "client")
    private List<Panier> paniers;

    @OneToMany
    private List<Commande> commandes;
}
