package com.officePharmaceutiqueNationale.OPN.controller;


import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.sercice.CommandeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/commandes")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class CommandeController {

    private final CommandeService commandeService;

    // Récupérer une commande existante par son ID
    @GetMapping("/{idCommande}")
    public ResponseEntity<CommandeDto> recupererCommandeParId(@PathVariable String idCommande) {
        CommandeDto commandeDto = commandeService.recupererCommandeParId(idCommande);
        return ResponseEntity.ok(commandeDto);
    }

    // Récupérer toutes les commandes existantes
    @GetMapping
    public ResponseEntity<List<CommandeDto>> recupererLesCommandes() {
        List<CommandeDto> commandesDto = commandeService.recupererLesCommandes();
        return new ResponseEntity<>(commandesDto, HttpStatus.OK);
    }

}
