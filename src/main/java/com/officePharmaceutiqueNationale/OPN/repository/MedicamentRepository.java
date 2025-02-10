 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.Medicament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


 @Repository
public interface MedicamentRepository extends JpaRepository<Medicament, String> {
     
}
