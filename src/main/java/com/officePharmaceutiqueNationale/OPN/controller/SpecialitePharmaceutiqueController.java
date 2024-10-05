package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.sercice.SpecialitePharmaceutiqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/specialites-pharmaceutiques")
public class SpecialitePharmaceutiqueController {

    private final SpecialitePharmaceutiqueService specialiteService;

    public SpecialitePharmaceutiqueController(SpecialitePharmaceutiqueService specialiteService) {
        this.specialiteService = specialiteService;
    }

    @PostMapping("/{idDci}")
    public ResponseEntity<SpecialitePharmaceutiqueDto> enregistrerUneSpecialite(
            @RequestBody SpecialitePharmaceutiqueDto specialiteDto,
            @PathVariable String idDci) {

        SpecialitePharmaceutiqueDto savedSpecialite = specialiteService.enregistrerUneSpecialite(specialiteDto, idDci);
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

}
