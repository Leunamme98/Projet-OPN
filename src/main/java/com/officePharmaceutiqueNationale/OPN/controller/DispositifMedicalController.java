package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.sercice.DispositifMedicalService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opn/api/dispositifs-medicaux")
@CrossOrigin(origins = "*")
public class DispositifMedicalController {

    private final DispositifMedicalService dispositifMedicalService;

    public DispositifMedicalController(DispositifMedicalService dispositifMedicalService) {
        this.dispositifMedicalService = dispositifMedicalService;
    }

    @PostMapping
    public ResponseEntity<DispositifMedicalDto> enregistrerDispositifMedical(@Valid @RequestBody DispositifMedicalDto dto) {
        DispositifMedicalDto createdDto = dispositifMedicalService.enregistrerUnDispositifMedical(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping
    public ResponseEntity<DispositifMedicalDto> modifierDispositifMedical(@Valid @RequestBody DispositifMedicalDto dto) {
        DispositifMedicalDto updatedDto = dispositifMedicalService.modifierUnDispositifMedical(dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerDispositifMedical(@PathVariable String id) {
        dispositifMedicalService.supprimerUnDispositifMedical(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DispositifMedicalDto> recupererDispositifMedicalParId(@PathVariable String id) {
        DispositifMedicalDto dto = dispositifMedicalService.recupererUnDispositifMedicalParId(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<DispositifMedicalDto>> recupererTousLesDispositifsMedicaux() {
        List<DispositifMedicalDto> dispositifsMedicaux = dispositifMedicalService.recupererLesDispositifsMedicaux();
        return ResponseEntity.ok(dispositifsMedicaux);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<DispositifMedicalDto>> recuperationParPagination(@RequestParam int page, @RequestParam int limit) {

        if (page < 0 || limit <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Page<DispositifMedicalDto> dispositifsMedicauxPage = dispositifMedicalService.recuperationParPagination(page, limit);
        return ResponseEntity.ok(dispositifsMedicauxPage);
    }

    @GetMapping("/metadonnees")
    public ResponseEntity<Page<DispositifMedicalDto>> recuperationDesMetadonnees(@RequestParam int page, @RequestParam int limit) {
        Page<DispositifMedicalDto> metadonnees = dispositifMedicalService.recuperationDesMetadonnees(page, limit);
        return ResponseEntity.ok(metadonnees);
    }
}
