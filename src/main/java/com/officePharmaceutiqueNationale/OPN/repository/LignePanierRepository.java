 package com.officePharmaceutiqueNationale.OPN.repository;


import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


 public interface LignePanierRepository extends JpaRepository<LignePanier, String> {

     List<LignePanier> findByPanierId(String PanierId) ;
    
}
