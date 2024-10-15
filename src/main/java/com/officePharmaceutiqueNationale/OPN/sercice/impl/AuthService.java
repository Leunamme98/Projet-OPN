package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.ReponseConnexionDto;
import com.officePharmaceutiqueNationale.OPN.model.Client;
import com.officePharmaceutiqueNationale.OPN.model.Utilisateur;
import com.officePharmaceutiqueNationale.OPN.repository.UtilisateurRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UtilisateurRepository utilisateurRepository;

    // Méthode pour authentifier un utilisateur
    public ReponseConnexionDto authentifierUtilisateur(String identifiantOuEmail, String motDePasse) {

        // Chercher un utilisateur par identifiant ou email
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByIdentifiantOrEmail(identifiantOuEmail, identifiantOuEmail);

        // Vérifier si l'utilisateur est trouvé
        if (utilisateurOptional.isPresent()) {
            Utilisateur utilisateur = utilisateurOptional.get();

            // Vérifier le mot de passe
            if (passwordEncoder.matches(motDePasse, utilisateur.getMotDePasseHache())) {
                String type = (utilisateur instanceof Client) ? "client" : "agent";
                return new ReponseConnexionDto(utilisateur.getId(), type);
            } else {
                throw new IllegalArgumentException("Mot de passe incorrect");
            }
        }

        // Si aucun utilisateur trouvé
        throw new IllegalArgumentException("Utilisateur non trouvé");
    }
}
