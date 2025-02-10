package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.sercice.SpecialitePharmaceutiqueService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/opn/api/specialites-pharmaceutiques")
public class SpecialitePharmaceutiqueController {

    private final SpecialitePharmaceutiqueService specialiteService;

    public SpecialitePharmaceutiqueController(SpecialitePharmaceutiqueService specialiteService) {
        this.specialiteService = specialiteService;
    }

    @PostMapping
    public ResponseEntity<SpecialitePharmaceutiqueDto> enregistrerUneSpecialite(
            @RequestBody SpecialitePharmaceutiqueDto specialiteDto) {

        if (specialiteDto.getDci() != null && specialiteDto.getDci().getId() != null) {
            // Enregistrer la spécialité avec le DCI associé
            specialiteDto.getDci().setId(specialiteDto.getDci().getId());
        }

        SpecialitePharmaceutiqueDto savedSpecialite = specialiteService.enregistrerUneSpecialite(specialiteDto);
        return ResponseEntity.status(201).body(savedSpecialite);
    }

    @PutMapping
    public ResponseEntity<SpecialitePharmaceutiqueDto> modifierUneSpecialite(@RequestBody SpecialitePharmaceutiqueDto specialiteDto) {
        SpecialitePharmaceutiqueDto updatedSpecialite = specialiteService.modifierUneSpecialite(specialiteDto);
        return ResponseEntity.ok(updatedSpecialite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUneSpecialite(@PathVariable String id) {
        specialiteService.supprimerUneSpecialite(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SpecialitePharmaceutiqueDto>> recupererLesSpecialites() {
        List<SpecialitePharmaceutiqueDto> specialites = specialiteService.recupererLesSpecialites();
        return ResponseEntity.ok(specialites);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialitePharmaceutiqueDto> recupererUneSpecialiteParId(@PathVariable String id) {
        SpecialitePharmaceutiqueDto specialiteDto = specialiteService.recupererUneSpecialiteParId(id);
        return ResponseEntity.ok(specialiteDto);
    }


    @GetMapping("/page")
    public ResponseEntity<Page<SpecialitePharmaceutiqueDto>> recuperationParPagination(
            @RequestParam int page, @RequestParam int limit) {
        Page<SpecialitePharmaceutiqueDto> specialitePage = specialiteService.recuperationParPagination(page, limit);
        return ResponseEntity.ok(specialitePage);
    }

    // Méthode non implémentée pour les métadonnées
    @GetMapping("/metadonnees")
    public ResponseEntity<Page<SpecialitePharmaceutiqueDto>> recuperationDesMetadonnees(
            @RequestParam int page, @RequestParam int limit) {
        // Implémentation spécifique selon vos besoins
        throw new UnsupportedOperationException("Méthode recuperationDesMetadonnees non implémentée");
    }
}
