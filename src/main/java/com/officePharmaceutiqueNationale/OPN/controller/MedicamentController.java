package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import com.officePharmaceutiqueNationale.OPN.sercice.MedicamentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicaments")
@CrossOrigin(origins = "*")
public class MedicamentController {

    private final MedicamentService medicamentService;

    public MedicamentController(MedicamentService medicamentService) {
        this.medicamentService = medicamentService;
    }

    // Enregistrer un nouveau médicament
    @PostMapping
    public ResponseEntity<MedicamentDto> enregistrerMedicament(@RequestBody MedicamentDto medicamentDto,
                                                               @RequestParam String idFormeGalenique,
                                                               @RequestParam String idSpecialitePharmaceutique) {
        MedicamentDto nouveauMedicament = medicamentService.enregistrerUnMedicament(medicamentDto, idFormeGalenique, idSpecialitePharmaceutique);
        return new ResponseEntity<>(nouveauMedicament, HttpStatus.CREATED);
    }

    // Modifier un médicament existant
    @PutMapping("/{id}")
    public ResponseEntity<MedicamentDto> modifierMedicament(@RequestBody MedicamentDto medicamentDto) {
        MedicamentDto medicamentModifie = medicamentService.modifierUnMedicament(medicamentDto);
        return new ResponseEntity<>(medicamentModifie, HttpStatus.OK);
    }

    // Récupérer un médicament par ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicamentDto> recupererMedicamentParId(@PathVariable String id) {
        MedicamentDto medicament = medicamentService.recupererUnMedicamentParId(id);
        return new ResponseEntity<>(medicament, HttpStatus.OK);
    }

    // Récupérer tous les médicaments
    @GetMapping
    public ResponseEntity<List<MedicamentDto>> recupererTousLesMedicaments() {
        List<MedicamentDto> medicaments = medicamentService.recupererLesMedicaments();
        return new ResponseEntity<>(medicaments, HttpStatus.OK);
    }

    // Supprimer un médicament
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerMedicament(@PathVariable String id) {
        medicamentService.supprimerUnMedicament(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Récupérer des médicaments avec pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<MedicamentDto>> recupererMedicamentsAvecPagination(@RequestParam int page, @RequestParam int limit) {
        Page<MedicamentDto> medicamentsPage = medicamentService.recuperationParPagination(page, limit);
        return new ResponseEntity<>(medicamentsPage, HttpStatus.OK);
    }
}
