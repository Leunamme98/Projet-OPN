package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.FormeGaleniqueDto;
import com.officePharmaceutiqueNationale.OPN.sercice.FormeGaleniqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/formes-galeniques")
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
        if (formeGaleniqueDto == null || formeGaleniqueDto.getNomFormeGalenique() == null || formeGaleniqueDto.getDescriptionFormeGalenique() == null) {
            return ResponseEntity.badRequest().build();
        }
        FormeGaleniqueDto savedFormeGalenique = formeGaleniqueService.enregistrerUneFormeGalenique(formeGaleniqueDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFormeGalenique);
    }

    // Modifier une forme galénique
    @PutMapping
    public ResponseEntity<FormeGaleniqueDto> modifierUneFormeGalenique(@RequestBody FormeGaleniqueDto formeGaleniqueDto) {
        if (formeGaleniqueDto == null || formeGaleniqueDto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        FormeGaleniqueDto updatedFormeGalenique = formeGaleniqueService.modifierUneFormeGalenique(formeGaleniqueDto);
        return ResponseEntity.ok(updatedFormeGalenique);
    }

    // Supprimer une forme galénique
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUneFormeGalenique(@PathVariable String id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        formeGaleniqueService.supprimerUneFormeGalenique(id);
        return ResponseEntity.noContent().build(); // Utilisation de 204 No Content
    }

    // Récupérer une forme galénique par ID
    @GetMapping("/{id}")
    public ResponseEntity<FormeGaleniqueDto> recupererUneFormeGaleniqueParId(@PathVariable String id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
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
        if (page < 0 || limit <= 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(formeGaleniqueService.recuperationParPagination(page, limit).getContent());
    }

    // Récupération des métadonnées par pagination
    @GetMapping("/metadonnees")
    public ResponseEntity<List<FormeGaleniqueDto>> recuperationDesMetadonnees(@RequestParam int page, @RequestParam int limit) {
        // Méthode non implémentée
        return ResponseEntity.notFound().build(); // Retourne 404 Not Found pour cette méthode non implémentée
    }
}
