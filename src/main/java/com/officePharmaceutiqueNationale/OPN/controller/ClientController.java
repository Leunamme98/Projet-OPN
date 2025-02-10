package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.ClientDto;
import com.officePharmaceutiqueNationale.OPN.sercice.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/client")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // Enregistrer un nouveau client
    @PostMapping
    public ResponseEntity<ClientDto> enregistrerClient(@Valid @RequestBody ClientDto clientDto) {
        ClientDto savedClient = clientService.enregistrerClient(clientDto);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    // Modifier un client existant
    @PutMapping
    public ResponseEntity<ClientDto> modifierClient(@Valid @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.modifierClient(clientDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    // Supprimer un client par son ID
    @DeleteMapping("/{idClient}")
    public ResponseEntity<Void> supprimerClient(@PathVariable String idClient) {
        clientService.supprimerClient(idClient);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Récupérer un client par son ID
    @GetMapping("/{idClient}")
    public ResponseEntity<ClientDto> recupererClientParId(@PathVariable String idClient) {
        ClientDto clientDto = clientService.recupererClientParId(idClient);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    // Récupérer la liste de tous les clients
    @GetMapping
    public ResponseEntity<List<ClientDto>> recupererTousLesClients() {
        List<ClientDto> clients = clientService.recupererTousLesClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    // Faire la pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<ClientDto>> recuperationParPagination(@RequestParam int page, @RequestParam int limit) {
        Page<ClientDto> pagedClients = clientService.recuperationParPagination(page, limit);
        return new ResponseEntity<>(pagedClients, HttpStatus.OK);
    }

    // Récupérer les métadonnées (non implémenté pour l'instant)
    @GetMapping("/metadonnees")
    public ResponseEntity<Void> recuperationDesMetadonnees() {
        return ResponseEntity.notFound().build(); // Renvoie une réponse 404 car cette méthode est encore nulle
    }
}
