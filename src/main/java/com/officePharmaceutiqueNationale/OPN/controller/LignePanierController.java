package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.sercice.LignePanierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/ligne-panier")
public class LignePanierController {

    private final LignePanierService lignePanierService;

    public LignePanierController(LignePanierService lignePanierService) {
        this.lignePanierService = lignePanierService;
    }

    // Créer une nouvelle ligne panier
    @PostMapping
    public ResponseEntity<LignePanierDto> creerLignePanier(@RequestBody LignePanierDto lignePanierDto) {
        LignePanierDto nouvelleLigne = lignePanierService.creerLignePanier(lignePanierDto);
        return ResponseEntity.ok(nouvelleLigne);
    }

    // Modifier la quantité d'une ligne panier
    @PutMapping("/{lignePanierId}")
    public ResponseEntity<LignePanierDto> modifierQuantiteLignePanier(@PathVariable String lignePanierId, @RequestParam int nouvelleQuantite) {
        LignePanierDto updatedLigne = lignePanierService.modifierQuantiteLignePanier(lignePanierId, nouvelleQuantite);
        return ResponseEntity.ok(updatedLigne);
    }

    // Supprimer une ligne panier
    @DeleteMapping("/{lignePanierId}")
    public ResponseEntity<Void> supprimerLignePanier(@PathVariable String lignePanierId) {
        lignePanierService.supprimerLignePanier(lignePanierId);
        return ResponseEntity.noContent().build();
    }

    // Récupérer une ligne panier par l'ID du panier
    @GetMapping("/panier/{panierId}")
    public ResponseEntity<List<LignePanierDto>> recupererLignesParPanierId(@PathVariable String panierId) {
        List<LignePanierDto> lignes = lignePanierService.recupererLignesParPanierId(panierId);
        return ResponseEntity.ok(lignes);
    }
}
