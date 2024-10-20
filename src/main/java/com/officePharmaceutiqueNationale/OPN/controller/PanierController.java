package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.dto.PanierDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.sercice.PanierService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/panier")
@AllArgsConstructor
public class PanierController {

    private PanierService panierService;

    // Endpoint pour obtenir le panier d'un client par son ID
    @GetMapping("/client/{idClient}")
    public ResponseEntity<PanierDto> obtenirPanierParIdClient(@PathVariable("idClient") String idClient) {

        PanierDto panierDto = panierService.obtenirPanierParIdClient(idClient);
        return ResponseEntity.ok(panierDto);

    }

    // Endpoint pour ajouter une ligne de panier
    @PostMapping("/lignes")
    public ResponseEntity<?> ajouterLigneAuPanier(@RequestBody LignePanierDto lignePanierDto) {
        try {
            // Appel au service pour ajouter la ligne de panier
            PanierDto panierMisAJour = panierService.ajouterLigneAuPanier(lignePanierDto);

            // Retourner le panier mis à jour avec un statut HTTP 200 OK
            return ResponseEntity.ok(panierMisAJour);

        } catch (IllegalArgumentException e) {
            // Gestion des erreurs de validation ou d'argument incorrect, avec un statut 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (ResourceNotFoundException e) {
            // Gestion du cas où le panier ou l'article n'existe pas, avec un statut 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        } catch (Exception e) {
            // Gestion des erreurs générales, avec un statut 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors de l'ajout ou de la mise à jour de la ligne au panier.");
        }
    }

    // Obtenir toutes les lignes paniers d'un panier
    @GetMapping("/{idPanier}/lignes")
    public ResponseEntity<List<LignePanierDto>> obtenirLignesPanier(@PathVariable String idPanier) {
        List<LignePanierDto> lignesPanier = panierService.obtenirLesLignesPanierParIdPanier(idPanier);

        if (lignesPanier.isEmpty()) {
            return ResponseEntity.notFound().build(); // Return 404 if no lines found
        }

        return ResponseEntity.ok(lignesPanier); // Return 200 with the list of lines
    }

    // Méthode pour modifier la quantité d'une ligne de panier
    @PutMapping("/lignes")
    public ResponseEntity<?> modifierLignePanier(@RequestBody LignePanierDto lignePanierDto) {
        try {
            // Appel du service pour modifier la ligne de panier
            PanierDto panierMisAJour = panierService.modifierLignePanier(lignePanierDto);
            // Retourner le panier mis à jour avec un statut HTTP 200 OK
            return ResponseEntity.ok(panierMisAJour);

        } catch (ResourceNotFoundException e) {
            // Gestion du cas où la ligne de panier n'existe pas, avec un statut 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());

        } catch (IllegalArgumentException e) {
            // Gestion des erreurs de validation ou d'argument incorrect, avec un statut 400 Bad Request
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());

        } catch (Exception e) {
            // Gestion des erreurs générales, avec un statut 500 Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors de la modification de la ligne de panier.");
        }
    }

    // Endpoint pour supprimer une ligne de panier
    @DeleteMapping("/lignes/{idLignePanier}")
    public ResponseEntity<PanierDto> supprimerLignePanier(@PathVariable String idLignePanier) {
        try {
            // Supprimer la ligne de panier et récupérer le panier mis à jour
            PanierDto panierMisAJour = panierService.supprimerLignePanier(idLignePanier);

            // Retourner le panier mis à jour avec un statut HTTP 200 OK
            return ResponseEntity.ok(panierMisAJour);

        } catch (ResourceNotFoundException e) {
            // Retourner un statut 404 Not Found si la ligne de panier n'existe pas
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        } catch (IllegalArgumentException e) {
            // Retourner un statut 400 Bad Request en cas d'erreur de validation
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        } catch (Exception e) {
            // Retourner un statut 500 Internal Server Error pour toute autre exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    // Endpoint pour valider un panier et le transformer en commande
    @PostMapping("/{idPanier}/valider")
    public ResponseEntity<CommandeDto> validerPanier(@PathVariable String idPanier) {

        try {
            CommandeDto commandeDto = panierService.validerPanier(idPanier);
            // Retourner la commande créée avec un statut HTTP 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(commandeDto);
        } catch (ResourceNotFoundException e) {
            // Gestion des erreurs si le panier ou le client n'est pas trouvé
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            // Gestion des autres erreurs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }


}


