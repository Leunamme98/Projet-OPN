package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import com.officePharmaceutiqueNationale.OPN.sercice.DciService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/opn/api/dci")
@CrossOrigin(origins = "*")
public class DciController {

    private final DciService dciService;

    public DciController(DciService dciService) {
        this.dciService = dciService;
    }

    // Enregistrer un nouveau DCI
    @PostMapping
    public ResponseEntity<DciDto> enregistrerUnDCI(@Valid @RequestBody DciDto dciDto) {
        DciDto createdDci = dciService.enregistrerUnDCI(dciDto);
        return new ResponseEntity<>(createdDci, HttpStatus.CREATED);
    }

    // Modifier un DCI existant
    @PutMapping
    public ResponseEntity<DciDto> modifierUnDCI(@Valid @RequestBody DciDto dciDto) {
        DciDto updatedDci = dciService.modifierUnDCI(dciDto);
        return new ResponseEntity<>(updatedDci, HttpStatus.OK);
    }

    // Supprimer un DCI existant
    @DeleteMapping("/{idDci}")
    public ResponseEntity<Void> supprimerUnDCI(@PathVariable String idDci) {
        dciService.supprimerUnDCI(idDci);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Récupérer un DCI existant par son ID
    @GetMapping("/{idDci}")
    public ResponseEntity<DciDto> recupererDciParId(@PathVariable String idDci) {
        DciDto dciDto = dciService.recupererDciParId(idDci);
        return ResponseEntity.ok(dciDto);
    }

    // Récupérer tous les DCIs existants
    @GetMapping
    public ResponseEntity<List<DciDto>> recupererLesDCI() {
        List<DciDto> dciList = dciService.recupererLesDCI();
        return new ResponseEntity<>(dciList, HttpStatus.OK);
    }

    // Récupérer des DCIs par pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<DciDto>> recuperationParPagination(@RequestParam int page, @RequestParam int limit) {
        Page<DciDto> dciPage = dciService.recuperationParPagination(page, limit);
        return new ResponseEntity<>(dciPage, HttpStatus.OK);
    }

    // Récupération des métadonnées d'une page
    @GetMapping("/metadonnees")
    public ResponseEntity<Page<DciDto>> recuperationDesMetadonnees(@RequestParam int page, @RequestParam int limit) {
        // Méthode non implémentée
        return ResponseEntity.notFound().build(); // Retourne 404 Not Found pour cette méthode non implémentée
    }
}
