package com.officePharmaceutiqueNationale.OPN.repository;


import com.officePharmaceutiqueNationale.OPN.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
    Optional<Utilisateur> findByIdentifiantOrEmail(String identifiant, String email);
}
