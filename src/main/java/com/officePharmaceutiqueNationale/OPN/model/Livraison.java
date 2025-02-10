package com.officePharmaceutiqueNationale.OPN.model;
import com.officePharmaceutiqueNationale.OPN.enums.EtatLivraison;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Livraison {

    @Id
    private String id;

    private LocalDate dateLivraison;
    private EtatLivraison etatLivraison;

    @ManyToOne
    private Commande commande;

}
