 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import org.springframework.data.jpa.repository.JpaRepository;
 

@RepositoryRestResource
public interface DispositifMedicalRepository extends JpaRepository<DispositifMedical, String> {
     
}
