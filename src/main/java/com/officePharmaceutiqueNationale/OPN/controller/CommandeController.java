package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.sercice.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/commandes")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    // Créer une nouvelle commande
    @PostMapping
    public ResponseEntity<CommandeDto> creerCommande(@RequestBody CommandeDto commandeDto) {
        CommandeDto newCommande = commandeService.creerCommande(commandeDto);
        return new ResponseEntity<>(newCommande, HttpStatus.CREATED);
    }

    // Récupérer une commande par son ID
    @GetMapping("/{id}")
    public ResponseEntity<CommandeDto> getCommandeById(@PathVariable("id") String commandeId) {
        CommandeDto commande = commandeService.getCommandeById(commandeId);
        return new ResponseEntity<>(commande, HttpStatus.OK);
    }

    // Récupérer toutes les commandes
    @GetMapping
    public ResponseEntity<List<CommandeDto>> getAllCommandes() {
        List<CommandeDto> commandes = commandeService.getAllCommandes();
        return new ResponseEntity<>(commandes, HttpStatus.OK);
    }

    // Mettre à jour l'état d'une commande
    @PutMapping("/{id}/etat")
    public ResponseEntity<CommandeDto> mettreAJourEtatCommande(@PathVariable("id") String commandeId, @RequestParam("etat") String nouvelEtat) {

        CommandeDto commandeMiseAJour = commandeService.mettreAJourEtatCommande(commandeId, nouvelEtat);

        return ResponseEntity.ok(commandeMiseAJour);
    }

    // Supprimer une commande
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerCommande(@PathVariable("id") String commandeId) {
        commandeService.supprimerCommande(commandeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}