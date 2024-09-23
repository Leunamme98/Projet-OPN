package com.officePharmaceutiqueNationale.OPN.repository;


import com.officePharmaceutiqueNationale.OPN.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "restUtilisateurs")
public interface UtilisateurRepository extends JpaRepository<Utilisateur, String> {
}
