 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialitePharmaceutiqueRepository extends JpaRepository<SpecialitePharmaceutique, String> {
   
}
