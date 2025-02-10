 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


 @Repository
public interface DispositifMedicalRepository extends JpaRepository<DispositifMedical, String> {
     
}
