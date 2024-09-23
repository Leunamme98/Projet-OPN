package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Livraison {

    @Id
    private String idLivraison;

    private LocalDate dateLivraison;
    private EtatLivraison etatLivraison;

    @ManyToOne
    private Commande commande;

    @OneToMany(mappedBy = "livraison")
    private List<LigneLivraison> ligneLivraisons;

}
