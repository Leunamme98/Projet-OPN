package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
 

 
public interface CommandeRepository extends JpaRepository<Commande, String> {
    
}
