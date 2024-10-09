package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.AgentOpnDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgentOpnService {

    // Enregistrer un nouvel agent
    AgentOpnDto enregistrerAgent(AgentOpnDto agentOpnDto);

    // Modifier un agent existant
    AgentOpnDto modifierAgent(AgentOpnDto agentOpnDto);

    // Supprimer un agent par son ID
    void supprimerAgent(String idAgent);

    // Récupérer un agent par son ID
    AgentOpnDto recupererAgentParId(String idAgent);

    // Récupérer la liste de tous les agents
    List<AgentOpnDto> recupererTousLesAgents();

    // Faire la pagination
    Page<AgentOpnDto> recuperationParPagination(int page, int limit);


    // Récupérer les métadonnées
    Page<AgentOpnDto> recuperationDesMetadonnees(int page, int limit);

}
