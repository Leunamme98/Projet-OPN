 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
 

 
public interface MedicamentRepository extends JpaRepository<Medicament, String> {
     
}
