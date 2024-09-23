 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.LigneLivraison;
import org.springframework.data.jpa.repository.JpaRepository;
 
 
public interface LigneLivraisonRepository extends JpaRepository<LigneLivraison, String> {
    // Ajoute ici des méthodes de requête personnalisées si nécessaire
}
