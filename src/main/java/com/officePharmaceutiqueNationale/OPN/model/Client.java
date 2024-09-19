package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Représente un client dans le système.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Utilisateur {

   /**
    * Nom de la structure du client.
    */
   private String nomStructure;

   /**
    * Numéro d'accréditation du client.
    */
   private String numeroAccreditation;

   /**
    * Commandes passées par le client.
    */
   @OneToMany(mappedBy = "client")
   private Set<Commande> commandes;



   /**
    * Ensemble des paniers liés au client.
    */
   @OneToMany(mappedBy = "client")
   private Set<Panier> paniers;
}
