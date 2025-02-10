package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.sercice.LignePanierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/opn/api/ligne-panier")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LignePanierController {

    private final LignePanierService lignePanierService;

    // Ajouter une ligne de panier
    @PostMapping
    public ResponseEntity<LignePanierDto> ajouterLignePanier(@Valid @RequestBody LignePanierDto lignePanierDto) {
        LignePanierDto createdLignePanier = lignePanierService.ajouterLignePanier(lignePanierDto);
        return new ResponseEntity<>(createdLignePanier, HttpStatus.CREATED);
    }

    // Modifier une ligne de panier
    @PutMapping
    public ResponseEntity<LignePanierDto> modifierLignePanier(@RequestBody LignePanierDto lignePanierDto) {
        LignePanierDto updatedLignePanier = lignePanierService.modifierLignePanier(lignePanierDto);
        return new ResponseEntity<>(updatedLignePanier, HttpStatus.OK);
    }

    // Supprimer une ligne de panier
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerLignePanier(@PathVariable String id) {
        lignePanierService.supprimerLignePanier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtenir une ligne de panier par ID
    @GetMapping("/{id}")
    public ResponseEntity<LignePanierDto> obtenirLignePanierParId(@PathVariable String id) {
        LignePanierDto lignePanierDto = lignePanierService.obtenirLignePanierParId(id);
        return new ResponseEntity<>(lignePanierDto, HttpStatus.OK);
    }

}
