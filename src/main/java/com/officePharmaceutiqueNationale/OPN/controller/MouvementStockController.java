package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.MouvementStockDto;
import com.officePharmaceutiqueNationale.OPN.sercice.MouvementStockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/mouvements-stock")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MouvementStockController {

    private final MouvementStockService mouvementStockService;

    // Enregistrer un nouveau mouvement de stock
    @PostMapping
    public ResponseEntity<MouvementStockDto> enregistrerMouvement(@Valid @RequestBody MouvementStockDto mouvementStockDto) {
        MouvementStockDto savedMouvement = mouvementStockService.enregistrerMouvement(mouvementStockDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMouvement);
    }

    // Obtenir tous les mouvements de stock
    @GetMapping
    public ResponseEntity<List<MouvementStockDto>> obtenirTousLesMouvements() {
        List<MouvementStockDto> mouvements = mouvementStockService.obtenirTousLesMouvements();
        return ResponseEntity.ok(mouvements);
    }

    // Obtenir un mouvement de stock par son ID
    @GetMapping("/{id}")
    public ResponseEntity<MouvementStockDto> obtenirMouvementParId(@PathVariable("id") String mouvementId) {
        MouvementStockDto mouvementStockDto = mouvementStockService.obtenirMouvementParId(mouvementId);
        return ResponseEntity.ok(mouvementStockDto);
    }

    // Obtenir les mouvements de stock par ID d'article
    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<MouvementStockDto>> obtenirMouvementsParArticle(@PathVariable("articleId") String articleId) {
        List<MouvementStockDto> mouvements = mouvementStockService.obtenirMouvementsParArticle(articleId);
        return ResponseEntity.ok(mouvements);
    }
}
