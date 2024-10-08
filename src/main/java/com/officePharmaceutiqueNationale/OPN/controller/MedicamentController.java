package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import com.officePharmaceutiqueNationale.OPN.sercice.MedicamentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/medicaments")
@CrossOrigin(origins = "*")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    // Enregistrer un nouveau médicament
    @PostMapping
    public ResponseEntity<MedicamentDto> enregistrerMedicament(@Valid @RequestBody MedicamentDto medicamentDto) {
        MedicamentDto savedMedicament = medicamentService.enregistrerUnMedicament(medicamentDto);
        return new ResponseEntity<>(savedMedicament, HttpStatus.CREATED);
    }

    // Modifier un médicament existant
    @PutMapping
    public ResponseEntity<MedicamentDto> modifierMedicament(@Valid @RequestBody MedicamentDto medicamentDto) {
        MedicamentDto updatedMedicament = medicamentService.modifierUnMedicament(medicamentDto);
        return new ResponseEntity<>(updatedMedicament, HttpStatus.OK);
    }

    // Supprimer un médicament
    @DeleteMapping("/{idMedicament}")
    public ResponseEntity<Void> supprimerMedicament(@PathVariable String idMedicament) {
        medicamentService.supprimerUnMedicament(idMedicament);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Récupérer un médicament par son ID
    @GetMapping("/{idMedicament}")
    public ResponseEntity<MedicamentDto> recupererMedicament(@PathVariable String idMedicament) {
        MedicamentDto medicament = medicamentService.recupererUnMedicamentParId(idMedicament);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }

    // Récupérer la liste des médicaments
    @GetMapping
    public ResponseEntity<List<MedicamentDto>> recupererMedicaments() {
        List<MedicamentDto> medicaments = medicamentService.recupererLesMedicaments();
        return new ResponseEntity<>(medicaments, HttpStatus.OK);
    }

    // Récupérer les médicaments avec pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<MedicamentDto>> recupererMedicamentsParPagination(@RequestParam int page, @RequestParam int limit) {
        if (page < 0 || limit <= 0) {
            return ResponseEntity.badRequest().build(); // Requête invalide
        }

        Page<MedicamentDto> medicamentsPage = medicamentService.recuperationParPagination(page, limit);
        return new ResponseEntity<>(medicamentsPage, HttpStatus.OK);
    }
}
