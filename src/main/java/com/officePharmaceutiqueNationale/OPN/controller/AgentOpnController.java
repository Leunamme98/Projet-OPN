package com.officePharmaceutiqueNationale.OPN.controller;

import com.officePharmaceutiqueNationale.OPN.dto.AgentOpnDto;
import com.officePharmaceutiqueNationale.OPN.sercice.AgentOpnService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/opn/api/agent-opn")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AgentOpnController {

    private final AgentOpnService agentOpnService;

    // Enregistrer un nouvel agent
    @PostMapping
    public ResponseEntity<AgentOpnDto> enregistrerAgent(@RequestBody AgentOpnDto agentOpnDto) {
        AgentOpnDto savedAgent = agentOpnService.enregistrerAgent(agentOpnDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAgent);
    }

    // Modifier un agent existant
    @PutMapping
    public ResponseEntity<AgentOpnDto> modifierAgent(@RequestBody AgentOpnDto agentOpnDto) {
        AgentOpnDto updatedAgent = agentOpnService.modifierAgent(agentOpnDto);
        return ResponseEntity.ok(updatedAgent);
    }

    // Supprimer un agent
    @DeleteMapping("/{idAgent}")
    public ResponseEntity<Void> supprimerAgent(@PathVariable String idAgent) {
        agentOpnService.supprimerAgent(idAgent);
        return ResponseEntity.noContent().build();
    }

    // Récupérer un agent par ID
    @GetMapping("/{idAgent}")
    public ResponseEntity<AgentOpnDto> recupererAgentParId(@PathVariable String idAgent) {
        AgentOpnDto agentOpnDto = agentOpnService.recupererAgentParId(idAgent);
        return ResponseEntity.ok(agentOpnDto);
    }

    // Récupérer tous les agents
    @GetMapping
    public ResponseEntity<List<AgentOpnDto>> recupererTousLesAgents() {
        List<AgentOpnDto> agents = agentOpnService.recupererTousLesAgents();
        return ResponseEntity.ok(agents);
    }

    // Récupérer des agents par pagination
    @GetMapping("/pagination")
    public ResponseEntity<Page<AgentOpnDto>> recuperationParPagination(@RequestParam int page, @RequestParam int limit) {
        Page<AgentOpnDto> agentsPage = agentOpnService.recuperationParPagination(page, limit);
        return ResponseEntity.ok(agentsPage);
    }

    // Récupérer les métadonnées (non implémenté pour l'instant)
    @GetMapping("/metadonnees")
    public ResponseEntity<Void> recuperationDesMetadonnees() {
        return ResponseEntity.notFound().build(); // Renvoie une réponse 404 car cette méthode est encore nulle
    }
}
