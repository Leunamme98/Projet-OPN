package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommandeRepository extends JpaRepository<Commande, String> {

    List<Commande> findByClientId(String clientId);

    boolean existsByNumeroCommande(String numeroCommande);
}
