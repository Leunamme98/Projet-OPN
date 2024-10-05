package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.FormeGaleniqueDto;
import com.officePharmaceutiqueNationale.OPN.sercice.FormeGaleniqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formes-galeniques")
@CrossOrigin(origins = "*")
public class FormeGaleniqueController {

    private final FormeGaleniqueService formeGaleniqueService;

    // Injection par constructeur
    public FormeGaleniqueController(FormeGaleniqueService formeGaleniqueService) {
        this.formeGaleniqueService = formeGaleniqueService;
    }

    // Enregistrer une nouvelle forme galénique
    @PostMapping
    public ResponseEntity<FormeGaleniqueDto> enregistrerUneFormeGalenique(@RequestBody FormeGaleniqueDto formeGaleniqueDto) {
        FormeGaleniqueDto savedFormeGalenique = formeGaleniqueService.enregistrerUneFormeGalenique(formeGaleniqueDto);
        return ResponseEntity.ok(savedFormeGalenique);
    }

    // Modifier une forme galénique
    @PatchMapping("/{id}")
    public ResponseEntity<FormeGaleniqueDto> modifierUneFormeGalenique(@PathVariable String id, @RequestBody FormeGaleniqueDto formeGaleniqueDto) {
        FormeGaleniqueDto updatedFormeGalenique = formeGaleniqueService.modifierUneFormeGalenique(id, formeGaleniqueDto);
        return ResponseEntity.ok(updatedFormeGalenique);
    }

    // Supprimer une forme galénique
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUneFormeGalenique(@PathVariable String id) {
        formeGaleniqueService.supprimerUneFormeGalenique(id);
        return ResponseEntity.noContent().build(); // Retourne un status 204 No Content
    }

    // Récupérer une forme galénique par ID
    @GetMapping("/{id}")
    public ResponseEntity<FormeGaleniqueDto> recupererUneFormeGaleniqueParId(@PathVariable String id) {
        FormeGaleniqueDto formeGalenique = formeGaleniqueService.recupererUneFormeGaleniqueParId(id);
        return ResponseEntity.ok(formeGalenique);
    }

    // Récupérer toutes les formes galéniques
    @GetMapping
    public ResponseEntity<List<FormeGaleniqueDto>> recupererLesFormesGaleniques() {
        List<FormeGaleniqueDto> formesGalenique = formeGaleniqueService.recupererLesFormesGaleniques();
        return ResponseEntity.ok(formesGalenique);
    }

    // Récupération par pagination
    @GetMapping("/pagination")
    public ResponseEntity<List<FormeGaleniqueDto>> recuperationParPagination(@RequestParam int page, @RequestParam int limit) {
        return ResponseEntity.ok(formeGaleniqueService.recuperationParPagination(page, limit).getContent());
    }

    // Récupération des métadonnées par pagination
    @GetMapping("/metadonnees")
    public ResponseEntity<List<FormeGaleniqueDto>> recuperationDesMetadonnees(@RequestParam int page, @RequestParam int limit) {
        return ResponseEntity.ok(formeGaleniqueService.recuperationDesMetadonnees(page, limit).getContent());
    }
}
