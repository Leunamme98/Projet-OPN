package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.sercice.PanierService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/panier")
public class PanierController {

    private final PanierService panierService;

    public PanierController(PanierService panierService) {
        this.panierService = panierService;
    }

    // Créer un nouveau panier
    @PostMapping
    public ResponseEntity<PanierDto> creerPanier(@RequestBody PanierDto panierDto) {
        PanierDto nouveauPanier = panierService.creerPanier(panierDto);
        return ResponseEntity.ok(nouveauPanier);
    }

    // Supprimer un panier
    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> supprimerPanier(@PathVariable String panierId) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.noContent().build();
    }

    // Récupérer un panier par l'ID du client
    @GetMapping("/client/{clientId}")
    public ResponseEntity<PanierDto> recupererPanierParClientId(@PathVariable String clientId) {
        PanierDto panier = panierService.recupererPanierParClientId(clientId);
        return ResponseEntity.ok(panier);
    }

    // Récupérer les lignes d'un panier
    @GetMapping("/{panierId}/lignes")
    public ResponseEntity<List<LignePanierDto>> recupererLignesPanierParPanierId(@PathVariable String panierId) {
        List<LignePanierDto> lignePanierDtos = panierService.recupererLignesPanierParPanierId(panierId);
        return ResponseEntity.ok(lignePanierDtos);
    }

    // Valider le panier
    @PostMapping("/{panierId}/valider")
    public ResponseEntity<Void> validerPanier(@PathVariable String panierId) {
        panierService.validerPanier(panierId);
        return ResponseEntity.ok().build();
    }
}
