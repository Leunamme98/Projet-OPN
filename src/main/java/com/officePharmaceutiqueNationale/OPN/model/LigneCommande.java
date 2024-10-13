package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class LigneCommande {

    @Id
    private String id;

    private int quantiteLigneCommande;
    private Double prixLigneCommande;

     @ManyToOne
    private Article article;

     @ManyToOne
     @JoinColumn(name="commande_id")
    private Commande commande;

     private Boolean isDeleted;

}
