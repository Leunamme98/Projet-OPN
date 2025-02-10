package com.officePharmaceutiqueNationale.OPN.model;
import com.officePharmaceutiqueNationale.OPN.enums.EtatCommande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Commande {

    @Id
    private String id;

    private Double montantTotalCommande;
    private String numeroCommande;
    LocalDateTime dateCommande;
    private EtatCommande etatCommande;


    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    /*
    @OneToOne
     private Facture facture;
    **/

}
