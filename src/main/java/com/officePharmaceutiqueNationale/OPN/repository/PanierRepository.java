 package com.officePharmaceutiqueNationale.OPN.repository;


import com.officePharmaceutiqueNationale.OPN.model.Panier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

 @Repository
public interface PanierRepository extends JpaRepository<Panier, String> {
     void deleteByClientId(String idClient);
 }

