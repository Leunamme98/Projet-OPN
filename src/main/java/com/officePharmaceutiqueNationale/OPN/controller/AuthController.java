package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.ReponseConnexionDto;
import com.officePharmaceutiqueNationale.OPN.sercice.impl.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("opn/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;


    // Endpoint pour la connexion d'un utilisateur
    @PostMapping("/connexion")
    public ResponseEntity<ReponseConnexionDto> connecterUtilisateur(
            @RequestParam("identifiantOuEmail") String identifiantOuEmail,
            @RequestParam("motDePasse") String motDePasse) {

        try {

            ReponseConnexionDto reponse = authService.authentifierUtilisateur(identifiantOuEmail, motDePasse);
            return ResponseEntity.ok(reponse);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
