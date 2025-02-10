 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.model.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


 public interface LigneCommandeRepository extends JpaRepository<LigneCommande, String> {

     List<LigneCommande> findByCommandeId(String commandeId) ;

}
